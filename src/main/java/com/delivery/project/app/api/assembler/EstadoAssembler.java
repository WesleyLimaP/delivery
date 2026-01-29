package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.CidadeController;
import com.delivery.project.app.api.controller.EstadoController;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.request.EstadoNomeDto;
import com.delivery.project.app.api.model.mapper.EstadoMapper;
import com.delivery.project.app.domain.model.Estado;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EstadoAssembler implements RepresentationModelAssembler<Estado, EstadoDto> {
    
    @Autowired
    private EstadoMapper estadoMapper;
    
    @Override
    public EstadoDto toModel(Estado entity) {
        var estadoDto = estadoMapper.toModel(entity);
        addLinks(estadoDto);
        return estadoDto;
    }
    
    @Override
    public CollectionModel<EstadoDto> toCollectionModel(Iterable<? extends Estado> entities) {
        var collection = RepresentationModelAssembler.super.toCollectionModel(entities);
        collection.add(linkTo(methodOn(EstadoController.class)
                .findAll())
                .withSelfRel());
        return collection;
    }
    public Estado toEntity(EstadoDto dto) {
        return estadoMapper.toEntity(dto);
    }
    public void update(@MappingTarget Estado estado, EstadoNomeDto dto){
        estadoMapper.update(estado, dto);
    }

    @AfterMapping
    private void addLinks(EstadoDto estadoDto) {
        estadoDto.add(linkTo(methodOn(EstadoController.class)
                .findById(estadoDto.getId()))
                .withSelfRel());
        estadoDto.add(linkTo(methodOn(EstadoController.class)
                .findAll())
                .withRel("estados"));
    }
}
