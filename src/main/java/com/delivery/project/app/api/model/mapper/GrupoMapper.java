package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.grupoDto.request.GrupoRequestDto;
import com.delivery.project.app.api.model.dto.grupoDto.response.GrupoResponseDto;
import com.delivery.project.app.domain.model.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GrupoMapper {
    
    GrupoResponseDto toModel(Grupo grupo);
    Grupo toEntity(GrupoRequestDto grupoDto);
    List<GrupoResponseDto> toCollectionModel(List<Grupo> grupos);
    void update(@MappingTarget Grupo grupo, GrupoRequestDto grupoDto);
}
