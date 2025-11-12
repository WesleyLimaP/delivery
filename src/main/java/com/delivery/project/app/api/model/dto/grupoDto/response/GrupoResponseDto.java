package com.delivery.project.app.api.model.dto.grupoDto.response;

import com.delivery.project.app.domain.model.Grupo;

public record GrupoResponseDto (Long id, String nome){

    public GrupoResponseDto(Grupo grupo) {
            this(grupo.getId(), grupo.getNome());
    }

}
