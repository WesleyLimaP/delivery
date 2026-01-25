package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.model.dto.filesDto.ImageDto;
import com.delivery.project.app.api.model.dto.fotoProduto.FotoProdutoDto;
import com.delivery.project.app.domain.model.FotoProduto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FotoProdutoAssembler extends RepresentationModelAssembler<FotoProduto, FotoProdutoDto> {
    @Override
    FotoProdutoDto toModel(FotoProduto fotoProduto);
    List<FotoProdutoDto> toCollectionModel(List<FotoProduto> fotoProdutos);
    FotoProduto toEntity(ImageDto imageDto);
    void update(@MappingTarget FotoProduto fotoProduto, FotoProdutoDto fotoProdutoDto);


}
