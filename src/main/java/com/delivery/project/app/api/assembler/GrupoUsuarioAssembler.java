package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.GrupoController;
import com.delivery.project.app.api.model.dto.usuarioDto.request.GrupoUsuarioRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.GrupoUsuarioResponseDto;
import com.delivery.project.app.domain.model.Grupo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface GrupoUsuarioAssembler extends RepresentationModelAssembler<Grupo, GrupoUsuarioResponseDto> {
    @Override
    GrupoUsuarioResponseDto toModel(Grupo grupo);
    List<GrupoUsuarioResponseDto> toCollectionModel(Set<Grupo> grupos);
    @AfterMapping
    default void addLinks(@MappingTarget GrupoUsuarioResponseDto grupoUsuarioResponseDto){
        grupoUsuarioResponseDto.add(linkTo(methodOn(GrupoController.class).getById(grupoUsuarioResponseDto.getId())).withSelfRel());
    }
}
