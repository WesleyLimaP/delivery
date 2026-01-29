package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.PedidoController;
import com.delivery.project.app.api.model.dto.itemPedidoDto.request.ItemPedidoRequestDto;
import com.delivery.project.app.api.model.dto.itemPedidoDto.response.ItemPedidoResponseDto;
import com.delivery.project.app.api.model.mapper.ItemPedidoMapper;
import com.delivery.project.app.domain.model.ItemPedido;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


public class ItemPedidoAssembler implements RepresentationModelAssembler<ItemPedido, ItemPedidoResponseDto> {

    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    @Override
    public ItemPedidoResponseDto toModel(ItemPedido entity) {
        return itemPedidoMapper.toModel(entity);
    }

    @Override
    public CollectionModel<ItemPedidoResponseDto> toCollectionModel(Iterable<? extends ItemPedido> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
    public ItemPedido toEntity(ItemPedidoRequestDto itemPedidoDto){
        return itemPedidoMapper.toEntity(itemPedidoDto);
    }
    public void update(@MappingTarget ItemPedido itemPedido, ItemPedidoRequestDto itemPedidoDto){
        itemPedidoMapper.update(itemPedido, itemPedidoDto);
    }
}
