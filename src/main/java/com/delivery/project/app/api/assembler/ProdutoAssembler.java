package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.GrupoPermissaoController;
import com.delivery.project.app.api.controller.RestauranteProdutoController;
import com.delivery.project.app.api.model.dto.permissaoDto.PermissaoDto;
import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.domain.model.Permissao;
import com.delivery.project.app.domain.model.Produto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface ProdutoAssembler extends RepresentationModelAssembler<Produto, ProdutoResponseDto> {

    @Override
    ProdutoResponseDto toModel(Produto produto);
    List<ProdutoResponseDto> toCollectionModel(List<Produto> produtos);
    void update(@MappingTarget Produto produto, ProdutoRequestDto produtoResponseDto);
    Produto toEntity(ProdutoRequestDto produtoRequestDto);



}
