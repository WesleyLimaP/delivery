package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.GrupoController;
import com.delivery.project.app.api.model.dto.grupoDto.request.GrupoRequestDto;
import com.delivery.project.app.api.model.dto.grupoDto.response.GrupoResponseDto;
import com.delivery.project.app.domain.exceptions.GrupoNaoEncontradoException;
import com.delivery.project.app.domain.model.Grupo;
import com.delivery.project.app.domain.repository.GrupoRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface GrupoAssembler extends RepresentationModelAssembler<Grupo, GrupoResponseDto> {
    GrupoResponseDto toModel(Grupo grupo);
    List<GrupoResponseDto> toCollectionModel(List<Grupo> grupos);
    void update(@MappingTarget Grupo grupo, GrupoRequestDto grupoResponseDto);
    Grupo toEntity(GrupoRequestDto grupoRequestDto);

    @AfterMapping
    default void addLinks(@MappingTarget GrupoResponseDto grupoResponseDto) {
        grupoResponseDto.add(linkTo(methodOn(GrupoController.class)
                .getById(grupoResponseDto.getId()))
                .withSelfRel());
    }
}
