package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.GrupoController;
import com.delivery.project.app.api.controller.GrupoPermissaoController;
import com.delivery.project.app.api.model.dto.grupoDto.request.GrupoRequestDto;
import com.delivery.project.app.api.model.dto.grupoDto.response.GrupoResponseDto;
import com.delivery.project.app.api.model.mapper.GrupoMapper;
import com.delivery.project.app.domain.model.Grupo;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GrupoAssembler implements RepresentationModelAssembler<Grupo, GrupoResponseDto> {
    
    @Autowired
    private GrupoMapper grupoMapper;
    
    @Override
    public GrupoResponseDto toModel(Grupo entity) {
        var grupoResponseDto = grupoMapper.toModel(entity);
        addLinks(grupoResponseDto);
        return grupoResponseDto;
    }
    
    @Override
    public CollectionModel<GrupoResponseDto> toCollectionModel(Iterable<? extends Grupo> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(methodOn(GrupoController.class)
                        .getAll())
                        .withSelfRel());
    }
    
    private void addLinks(@MappingTarget GrupoResponseDto grupoResponseDto) {
        grupoResponseDto.add(linkTo(methodOn(GrupoController.class)
                .getById(grupoResponseDto.getId()))
                .withSelfRel());
        grupoResponseDto.add(linkTo(methodOn(GrupoController.class)
                .getAll())
                .withRel("grupos"));
        grupoResponseDto.add(linkTo(methodOn(GrupoPermissaoController.class)
                .findAllPermissoes(grupoResponseDto.getId()))
                .withRel("permissoes"));
    }

    public Grupo toEntity(GrupoRequestDto dto) {
        return grupoMapper.toEntity(dto);
    }

    public void update(Grupo grupo, GrupoRequestDto dto) {
        grupoMapper.update(grupo, dto);
    }
}
