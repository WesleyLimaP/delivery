package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.api.model.mapper.utils.RestauranteMap;
import com.delivery.project.app.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoResponseDto toModel(Produto produto);
    List<ProdutoResponseDto> toCollectionModel(List<Produto> produtos);
    void update(@MappingTarget Produto produto, ProdutoRequestDto produtoResponseDto);
    Produto toEntity(ProdutoRequestDto produtoRequestDto);
}
