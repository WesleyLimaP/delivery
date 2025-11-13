package com.delivery.project.app.api.model.dto.usuarioDto.response;

import com.delivery.project.app.domain.model.Grupo;

public record GrupoUsuarioResponseDto(Long id, String nome) {
    public GrupoUsuarioResponseDto(Grupo x) {
        this(x.getId(), x.getNome());
    }
}
