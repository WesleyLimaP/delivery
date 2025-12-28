package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.GrupoUsuarioRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioPostRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateSenhaDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.GrupoUsuarioResponseDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMaxResponse;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMinResponse;
import com.delivery.project.app.domain.model.Grupo;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.domain.model.Usuario;
import com.delivery.project.app.exceptions.*;
import com.delivery.project.app.domain.repository.GrupoRepository;
import com.delivery.project.app.domain.repository.RestauranteRepository;
import com.delivery.project.app.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public List<UsuarioMinResponse> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios.stream().map(UsuarioMinResponse::new).toList();
    }

    @Transactional(readOnly = true)
    public UsuarioMaxResponse findById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("usuario nao encontrado"));

        return new UsuarioMaxResponse(usuario);
    }

    @Transactional()
    public UsuarioMaxResponse insert(UsuarioPostRequestDto dto) {
        Usuario usuario = mergeDtoToEntity(dto);
        verificarEmail(dto.getEmail(), usuario);
        usuario = repository.save(usuario);
        return new UsuarioMaxResponse(usuario);

    }

    private void verificarEmail(String email, Usuario usuario) {
        Optional<Usuario> emailResult = repository.findByEmail(email);
        if(repository.findByEmail(email).isPresent() && !usuario.equals(emailResult.get())){
           throw new EntidadeEmUsoException("o email fornecido ja existe");
        }
    }

    private Usuario mergeDtoToEntity(UsuarioPostRequestDto dto) {
        List<Grupo> grupos = getGrupos(dto);
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setSenha(dto.getSenha());
        usuario.getGrupos().addAll(grupos);
        usuario.setEmail(dto.getEmail());
        return usuario;
    }

    private List<Grupo> getGrupos(UsuarioPostRequestDto dto) {
        return new ArrayList<>(dto.getGrupos().stream()
                .map(this::getGrupoOrElseThrow)
                .toList());
    }

    private Grupo getGrupoOrElseThrow(GrupoUsuarioRequestDto x) {
        return grupoRepository.findById(x.id())
                .orElseThrow(() ->
                        new GrupoNaoEncontradoException(" nao foi possivel encntrar o grupo com id informado"));
    }
    private Grupo getGrupoOrElseThrow(Long id) {
        return grupoRepository.findById(id)
                .orElseThrow(() ->
                        new GrupoNaoEncontradoException(" nao foi possivel encntrar o grupo com id informado"));
    }

    @Transactional
    public void delete(Long id) {
        try {
            Usuario usuario = getOrElseThrow(id);
            repository.delete(usuario);
            repository.flush();
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException("nao será possivel deletar o usuario informado pois outras entidades dependem dele");
        }
    }

    private Usuario getOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("usuario nao econtrado"));
    }
    private Restaurante getRestauranteOrElseThrow(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException("restaurante nao econtrado"));
    }

    @Transactional
    public UsuarioMaxResponse update(Long id, UsuarioUpdateRequestDto dto) {
        Usuario usuario = getOrElseThrow(id);
        verificarEmail(dto.email(), usuario);
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        return new UsuarioMaxResponse(usuario);
    }
    @Transactional
    public UsuarioMaxResponse update(Long id, UsuarioUpdateSenhaDto dto) {
        Usuario usuario = getOrElseThrow(id);
        usuario.setSenha(dto.senha());
        return new UsuarioMaxResponse(usuario);
    }

    @Transactional(readOnly = true)
    public List<GrupoUsuarioResponseDto> findAllGrupos(Long usuarioId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        return usuario.getGrupos().stream().map(GrupoUsuarioResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public GrupoUsuarioResponseDto findGrupoById(Long usuarioId, Long grupoId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        Grupo grupo = getGrupoOrElseThrow(grupoId);
        verificarAssociacaoGrupo(usuario, grupo);
        return new GrupoUsuarioResponseDto(grupo);

    }

    private void verificarAssociacaoGrupo(Usuario usuario, Grupo grupo) {
        if(!usuario.getGrupos().contains(grupo)){throw new AssociacaoException("o usuario nao pertence a esse grupo");
        }
    }
    private void verificarAssociacaoRestaurante(Usuario usuario, Restaurante restaurante) {
        if(!usuario.getRestaurantes().contains(restaurante)){throw new AssociacaoException("o usuario nao pertence a esse restaurante");
        }
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        Grupo grupo = getGrupoOrElseThrow(grupoId);
        verificarAssociacaoGrupo(usuario, grupo);
        usuario.getGrupos().remove(grupo);
    }

    @Transactional
    public GrupoUsuarioResponseDto associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        Grupo grupo = getGrupoOrElseThrow(grupoId);
        usuario.getGrupos().add(grupo);
        return new GrupoUsuarioResponseDto(grupo);
    }

    @Transactional(readOnly = true)
    public RestauranteDto findRestauranteByid(Long usuarioId, Long restauranteId) {
        Restaurante restaurante = getRestauranteOrElseThrow(restauranteId);
        Usuario usuario = getOrElseThrow(usuarioId);
        verificarAssociacaoRestaurante(usuario, restaurante);
        return  new RestauranteDto(restaurante);

    }
    @Transactional(readOnly = true)
    public List<RestauranteDto> findAllRestaurante(Long usuarioId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        return  usuario.getRestaurantes().stream().map(RestauranteDto::new).toList();

    }
    @Transactional
    public void desassociarRestaurante(Long usuarioId, Long restauranteId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        Restaurante restaurante = getRestauranteOrElseThrow(restauranteId);
        verificarAssociacaoRestaurante(usuario, restaurante);
        usuario.getRestaurantes().remove(restaurante);
    }

    @Transactional
    public RestauranteDto associarRestaurante(Long usuarioId, Long restauranteId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        Restaurante restaurante = getRestauranteOrElseThrow(restauranteId);
        usuario.getRestaurantes().add(restaurante);
        return new RestauranteDto(restaurante);
    }
}
