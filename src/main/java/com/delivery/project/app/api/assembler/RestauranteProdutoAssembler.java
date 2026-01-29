package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.RestauranteProdutoController;
import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.api.model.mapper.ProdutoMapper;
import com.delivery.project.app.domain.model.Produto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RestauranteProdutoAssembler implements RepresentationModelAssembler<Produto, ProdutoResponseDto> {
    @Autowired
    private ProdutoMapper produtoMapper;
    @Override
    public ProdutoResponseDto toModel(Produto entity){
        var produtoResponseDto = produtoMapper.toModel(entity);
        addLink(produtoResponseDto, entity);
        return produtoResponseDto;
    };

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
    };
    public void update(@MappingTarget Produto produto, ProdutoRequestDto produtoDto){
        produtoMapper.update(produto, produtoDto);
    };


    private void addLink(@MappingTarget ProdutoResponseDto produtoResponseDto, Produto produto){
        produtoResponseDto
                .add(linkTo(methodOn(RestauranteProdutoController.class)
                        .findProdutoById(produto.getRestaurante().getId(), produtoResponseDto.getId()))
                        .withSelfRel());

    }






}
