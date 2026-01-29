package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.assembler.GrupoUsuarioAssembler;
import com.delivery.project.app.api.assembler.RestauranteAssembler;
import com.delivery.project.app.api.assembler.UsuarioMaxAssembler;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioPostRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateSenhaDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.GrupoUsuarioResponseDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMaxResponse;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMinResponse;
import com.delivery.project.app.domain.exceptions.*;
import com.delivery.project.app.domain.model.Grupo;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.domain.model.Usuario;
import com.delivery.project.app.domain.repository.GrupoRepository;
import com.delivery.project.app.domain.repository.RestauranteRepository;
import com.delivery.project.app.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private UsuarioMaxAssembler usuarioMaxAssembler;
    @Autowired
    private UsuarioMinAssembler usuarioMinAssembler;
    @Autowired
    private GrupoUsuarioAssembler grupoUsuarioAssembler;
    @Autowired
    private RestauranteAssembler restauranteAssembler;

    @Transactional(readOnly = true)
    public List<UsuarioMinResponse> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return usuarioMinAssembler.toCollectionModel(usuarios);
    }

    @Transactional(readOnly = true)
    public UsuarioMaxResponse findById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("usuario nao encontrado"));

        return usuarioMaxAssembler.toModel(usuario);
    }

    @Transactional()
    public UsuarioMaxResponse insert(UsuarioPostRequestDto dto) {
        Usuario usuario = usuarioMaxAssembler.toEntity(dto);
        verificarEmail(dto.getEmail(), usuario);
        usuario = repository.save(usuario);
        return usuarioMaxAssembler.toModel(usuario);

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


    @Transactional
    public UsuarioMaxResponse update(Long id, UsuarioUpdateRequestDto dto) {
        Usuario usuario = getOrElseThrow(id);
        verificarEmail(dto.email(), usuario);
        usuarioMaxAssembler.update(usuario, dto);
        return usuarioMaxAssembler.toModel(usuario);
    }
    @Transactional
    public UsuarioMaxResponse update(Long id, UsuarioUpdateSenhaDto dto) {
        Usuario usuario = getOrElseThrow(id);
        usuario.setSenha(dto.senha());
        repository.flush();
        return usuarioMaxAssembler.toModel(usuario);
    }

    @Transactional(readOnly = true)
    public CollectionModel<GrupoUsuarioResponseDto> findAllGrupos(Long usuarioId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        return grupoUsuarioAssembler.toCollectionModel(usuario.getGrupos());
    }

    @Transactional(readOnly = true)
    public GrupoUsuarioResponseDto findGrupoById(Long usuarioId, Long grupoId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        Grupo grupo = getGrupoOrElseThrow(grupoId);
        usuario.verificarAssociacaoGrupo(grupo);
        throwExceptionIfNotGroupAssociated(usuario.verificarAssociacaoGrupo(grupo));
        return grupoUsuarioAssembler.toModel(grupo);

    }



    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        Grupo grupo = getGrupoOrElseThrow(grupoId);
        var assossiacao = usuario.verificarAssociacaoGrupo(grupo);
        throwExceptionIfNotGroupAssociated(assossiacao);
        usuario.getGrupos().remove(grupo);
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        Grupo grupo = getGrupoOrElseThrow(grupoId);
        usuario.getGrupos().add(grupo);
    }

    @Transactional(readOnly = true)
    public RestauranteDto findRestauranteByid(Long usuarioId, Long restauranteId) {
        Restaurante restaurante = getRestauranteOrElseThrow(restauranteId);
        Usuario usuario = getOrElseThrow(usuarioId);
        var assossiacao = usuario.verificarAssociacaoRestaurante(restaurante);
        throwExceptionIfNotRestauranteAssociated(assossiacao);
        return restauranteAssembler.toModel(restaurante);

    }
    @Transactional(readOnly = true)
    public CollectionModel<RestauranteDto> findAllRestaurante(Long usuarioId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        return  restauranteAssembler.toCollectionModel(usuario.getRestaurantes().stream().toList());

    }
    @Transactional
    public void desassociarRestaurante(Long usuarioId, Long restauranteId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        Restaurante restaurante = getRestauranteOrElseThrow(restauranteId);
        var associacao = usuario.verificarAssociacaoRestaurante(restaurante);
        throwExceptionIfNotRestauranteAssociated(associacao);
        usuario.getRestaurantes().remove(restaurante);
    }

    @Transactional
    public RestauranteDto associarRestaurante(Long usuarioId, Long restauranteId) {
        Usuario usuario = getOrElseThrow(usuarioId);
        Restaurante restaurante = getRestauranteOrElseThrow(restauranteId);
        usuario.getRestaurantes().add(restaurante);
        return restauranteAssembler.toModel(restaurante);
    }
    private void verificarEmail(String email, Usuario usuario) {
        Optional<Usuario> emailResult = repository.findByEmail(email);
        if(repository.findByEmail(email).isPresent() && !usuario.equals(emailResult.get())){
            throw new EntidadeEmUsoException("o email fornecido ja existe");
        }
    }

    private Usuario getOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("usuario nao econtrado"));
    }
    private Restaurante getRestauranteOrElseThrow(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException("restaurante nao econtrado"));
    }
    private Grupo getGrupoOrElseThrow(Long id) {
        return grupoRepository.findById(id)
                .orElseThrow(() ->
                        new GrupoNaoEncontradoException(" nao foi possivel encntrar o grupo com id informado"));
    }
    private void throwExceptionIfNotGroupAssociated(boolean associacaoGrupo) {
        if(!associacaoGrupo){throw new AssociacaoException("o usuario nao pertence a esse grupo");
        }
    }
    private void throwExceptionIfNotRestauranteAssociated(boolean associacaoRestaurante) {
        if(associacaoRestaurante){throw new AssociacaoException("o usuario nao pertence a esse restaurante");
        }
    }

}
