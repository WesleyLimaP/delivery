package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.RestauranteProdutoController;
import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.api.model.mapper.ProdutoMapper;
import com.delivery.project.app.domain.model.Produto;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProdutoAssembler implements RepresentationModelAssembler<Produto, ProdutoResponseDto> {
    
    @Autowired
    private ProdutoMapper produtoMapper;
    
    @Override
    public ProdutoResponseDto toModel(Produto entity) {
        var model=  produtoMapper.toModel(entity);
        addLinks(model, entity);
        return model;
    }
    
    @Override
    public CollectionModel<ProdutoResponseDto> toCollectionModel(Iterable<? extends Produto> entities) {
        var collection = RepresentationModelAssembler.super.toCollectionModel(entities);
        collection.add(linkTo(methodOn(RestauranteProdutoController.class)
                .findAllProduto(entities.iterator().next().getRestaurante().getId()))
                .withSelfRel());
        return collection;
    }
    public Produto toEntity(ProdutoRequestDto dto){
        return produtoMapper.toEntity(dto);
    }
    public void update(@MappingTarget Produto produto, ProdutoRequestDto dto){
        produtoMapper.update(produto, dto);
    }

    @AfterMapping
    public void addLinks(@MappingTarget ProdutoResponseDto produtoResponseDto, Produto produto){
        produtoResponseDto.add(linkTo(methodOn(RestauranteProdutoController.class)
                .findProdutoById(produto.getRestaurante().getId(), produtoResponseDto.getId()))
                .withSelfRel());
    }
}
