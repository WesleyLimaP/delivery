package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.RestauranteProdutoController;
import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.domain.model.Produto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface RestauranteProdutoAssembler extends RepresentationModelAssembler<Produto, ProdutoResponseDto> {

    @Override
    ProdutoResponseDto toModel(Produto entity);
    List<ProdutoResponseDto> toCollectionModel(List<Produto> entities);
    Produto toEntity(ProdutoRequestDto dto);
    void update(@MappingTarget Produto produto, ProdutoRequestDto produtoDto);

    @AfterMapping
    default void addLink(@MappingTarget ProdutoResponseDto produtoResponseDto, Produto produto){
        produtoResponseDto
                .add(linkTo(methodOn(RestauranteProdutoController.class)
                        .findProdutoById(produto.getRestaurante().getId(), produtoResponseDto.getId()))
                        .withSelfRel());

    }






}
