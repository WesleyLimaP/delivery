package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.PermissaoController;
import com.delivery.project.app.api.model.dto.permissaoDto.PermissaoDto;
import com.delivery.project.app.api.model.mapper.PermissaoMapper;
import com.delivery.project.app.domain.model.Permissao;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PermissaoAssembler implements RepresentationModelAssembler<Permissao, PermissaoDto> {
    
    @Autowired
    private PermissaoMapper permissaoMapper;
    
    @Override
    public PermissaoDto toModel(Permissao entity) {
        return permissaoMapper.toModel(entity);
    }
    
    public CollectionModel<PermissaoDto> toCollectionModel(List<Permissao> permissoes, Long grupoId){
        return CollectionModel
                .of(permissoes
                        .stream()
                        .map(x -> toModel(x, grupoId))
                        .collect(Collectors.toList()))
                .add(linkTo(methodOn(PermissaoController.class)
                        .findAll())
                        .withSelfRel());
    }
    
    @Override
    public CollectionModel<PermissaoDto> toCollectionModel(Iterable<? extends Permissao> entities) {
        var collection = RepresentationModelAssembler.super.toCollectionModel(entities);
        collection.add(linkTo(methodOn(PermissaoController.class)
                .findAll())
                .withSelfRel());
        return collection;
    }
    
    public PermissaoDto toModel(Permissao permissao, Long grupoid){
        var dto = permissaoMapper.toModel(permissao);
        dto.setGrupoId(grupoid);
        return dto;
    }
}
