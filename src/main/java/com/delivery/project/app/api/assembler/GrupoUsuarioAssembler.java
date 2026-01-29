package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.GrupoController;
import com.delivery.project.app.api.model.dto.usuarioDto.request.GrupoUsuarioRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.GrupoUsuarioResponseDto;
import com.delivery.project.app.api.model.mapper.GrupoUsuarioMapper;
import com.delivery.project.app.domain.model.Grupo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public class GrupoUsuarioAssembler implements RepresentationModelAssembler<Grupo, GrupoUsuarioResponseDto> {

    @Autowired
    private GrupoUsuarioMapper grupoUsuarioMapper;

    @Override
    public GrupoUsuarioResponseDto toModel(Grupo grupo){
        var model = grupoUsuarioMapper.toModel(grupo);
        addLinks(model);
        return model;
    };

    @Override
    public CollectionModel<GrupoUsuarioResponseDto> toCollectionModel(Iterable<? extends Grupo> entities) {
        var collection = RepresentationModelAssembler.super.toCollectionModel(entities);
        collection.add(linkTo(methodOn(GrupoController.class)
                .getAll())
                .withSelfRel());
        return collection;
    }

    public Grupo toEntity(GrupoUsuarioRequestDto dto){
        return grupoUsuarioMapper.toEntity(dto);
    };
    public void update(@MappingTarget Grupo grupo, GrupoUsuarioResponseDto dto){
        grupoUsuarioMapper.update(grupo, dto);
    };

    private void addLinks(@MappingTarget GrupoUsuarioResponseDto grupoUsuarioResponseDto){
        grupoUsuarioResponseDto
                .add(linkTo(methodOn(GrupoController.class)
                        .getById(grupoUsuarioResponseDto
                                .getId())).withSelfRel());
        grupoUsuarioResponseDto
                .add(linkTo(methodOn(GrupoController.class)
                        .getAll())
                        .withRel("grupos"));

    }
}
