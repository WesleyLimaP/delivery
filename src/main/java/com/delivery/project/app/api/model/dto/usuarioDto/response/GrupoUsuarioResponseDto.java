package com.delivery.project.app.api.model.dto.usuarioDto.response;

import com.delivery.project.app.domain.model.Grupo;

//essa classe nao tem nada haver com a classe intermediaria entre grupo e usuario.
// ela so modela um grupo para setar o relacionamento com usuario

public record GrupoUsuarioResponseDto(Long id, String nome) {
    public GrupoUsuarioResponseDto(Grupo x) {
        this(x.getId(), x.getNome());
    }
}
