package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.PedidoController;
import com.delivery.project.app.api.controller.RestauranteController;
import com.delivery.project.app.api.controller.UsuarioController;
import com.delivery.project.app.api.model.dto.pedidoDto.request.PedidoRequestDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoDto;
import com.delivery.project.app.api.model.filter.PedidoFilter;
import com.delivery.project.app.api.model.mapper.PedidoMapper;
import com.delivery.project.app.domain.model.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;




@Component
public class PedidoAssembler implements RepresentationModelAssembler<Pedido, PedidoDto>{
    @Autowired
    private PedidoMapper pedidoMapper;

    @Override
    public PedidoDto toModel(Pedido entity) {
        var model =  pedidoMapper.toModel(entity);
        addLinks(model);
        return model;
    }
    public Pedido toEntity(PedidoRequestDto pedidoDto){
        return pedidoMapper.toEntity(pedidoDto);
    };

    @Override
    public CollectionModel<PedidoDto> toCollectionModel(Iterable<? extends Pedido> entities) {
        var collection = RepresentationModelAssembler.super.toCollectionModel(entities);
        collection.add(WebMvcLinkBuilder
                .linkTo(methodOn(PedidoController.class)
                        .findAll(new PedidoFilter(), Pageable.ofSize(1)))
                .withSelfRel());
        return collection;
    }

    @AfterMapping
    void addLinks(@MappingTarget PedidoDto pedidoDto) {
        pedidoDto.add(WebMvcLinkBuilder.linkTo(methodOn(PedidoController.class).findById(pedidoDto.getId())).withSelfRel());
        pedidoDto.getCliente().add(WebMvcLinkBuilder.linkTo(methodOn(UsuarioController.class)
                .findById(pedidoDto.getCliente().getId()))
                .withRel("cliente"));
        pedidoDto.getRestaurante().add(WebMvcLinkBuilder.linkTo(methodOn(RestauranteController.class)
                .findById(pedidoDto.getRestaurante().getId()))
                .withRel("restaurante"));


    }

}