package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.model.dto.usuarioDto.request.GrupoUsuarioRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioPostRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateSenhaDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMaxResponse;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMinResponse;
import com.delivery.project.app.domain.model.Grupo;
import com.delivery.project.app.domain.model.Usuario;
import com.delivery.project.app.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.exceptions.GrupoNaoEncontradoException;
import com.delivery.project.app.exceptions.UsuarioNaoEncontradoException;
import com.delivery.project.app.repository.GrupoRepository;
import com.delivery.project.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private GrupoRepository grupoRepository;

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
        usuario = repository.save(usuario);
        return new UsuarioMaxResponse(usuario);
    }

    private Usuario mergeDtoToEntity(UsuarioPostRequestDto dto) {
        List<Grupo> grupos = getGrupos(dto);
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setSenha(dto.getSenha());
        usuario.getGrupos().addAll(grupos);
        return usuario;
    }

    private List<Grupo> getGrupos(UsuarioPostRequestDto dto) {
        return new ArrayList<>(dto.getGrupos().stream()
                .map(this::getOrElseThrow)
                .toList());
    }

    private Grupo getOrElseThrow(GrupoUsuarioRequestDto x) {
        return grupoRepository.findById(x.id())
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

    @Transactional
    public UsuarioMaxResponse update(Long id, UsuarioUpdateRequestDto dto) {
        Usuario usuario = getOrElseThrow(id);
        usuario.setNome(dto.nome());
        return new UsuarioMaxResponse(usuario);
    }
    @Transactional
    public UsuarioMaxResponse update(Long id, UsuarioUpdateSenhaDto dto) {
        Usuario usuario = getOrElseThrow(id);
        usuario.setSenha(dto.senha());
        return new UsuarioMaxResponse(usuario);
    }
}
