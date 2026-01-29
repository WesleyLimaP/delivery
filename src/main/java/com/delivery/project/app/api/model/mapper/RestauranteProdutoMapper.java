package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteProdutoMapper {

    ProdutoResponseDto toModel(Produto entity);
    List<ProdutoResponseDto> toCollectionModel(List<Produto> entities);
    Produto toEntity(ProdutoRequestDto dto);
    void update(@MappingTarget Produto produto, ProdutoRequestDto produtoDto);
}
