package com.delivery.project.app.api.model.mapper.utils;

import com.delivery.project.app.api.model.dto.usuarioDto.request.GrupoUsuarioRequestDto;
import com.delivery.project.app.domain.exceptions.GrupoNaoEncontradoException;
import com.delivery.project.app.domain.model.Grupo;
import com.delivery.project.app.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoMap {
    @Autowired
    private GrupoRepository repository;

    public Grupo fromId(GrupoUsuarioRequestDto grupo) {
        return repository.findById(grupo.id())
                .orElseThrow(() -> new GrupoNaoEncontradoException(" nao foi possivel encontrar o grupo com id informado"));
    }
}
