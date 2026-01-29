package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.usuarioDto.request.GrupoUsuarioRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.GrupoUsuarioResponseDto;
import com.delivery.project.app.domain.model.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GrupoUsuarioMapper {
    
    GrupoUsuarioResponseDto toModel(Grupo grupo);
    List<GrupoUsuarioResponseDto> toCollectionModel(Set<Grupo> grupos);
    Grupo toEntity(GrupoUsuarioRequestDto dto);
    void update(@MappingTarget Grupo grupo, GrupoUsuarioResponseDto grupoUsuarioResponseDto);
}
