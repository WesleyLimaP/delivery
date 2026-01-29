package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.CozinhaController;
import com.delivery.project.app.api.controller.RestauranteController;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteInsertDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteUpdateDto;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.api.model.mapper.RestauranteMapper;
import com.delivery.project.app.api.model.mapper.utils.CidadeMap;
import com.delivery.project.app.api.model.mapper.utils.CozinhaMap;
import com.delivery.project.app.api.model.mapper.utils.FormaDePagamentoMap;
import com.delivery.project.app.domain.model.Restaurante;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RestauranteAssembler implements RepresentationModelAssembler<Restaurante, RestauranteDto> {

    @Autowired
    private RestauranteMapper restauranteMapper;

    @Override
    public RestauranteDto toModel(Restaurante restaurante){
        var restauranteDto = restauranteMapper.toModel(restaurante);
        addLinks(restauranteDto);
        return restauranteDto;
    };
    public Restaurante toEntity(RestauranteInsertDto dto){
        return restauranteMapper.toEntity(dto);
    };

    @Override
    public CollectionModel<RestauranteDto> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public void update(@MappingTarget Restaurante restaurante, RestauranteUpdateDto dto){
        restauranteMapper.update(restaurante, dto);
    };


    @AfterMapping
    private void addLinks(@MappingTarget RestauranteDto restauranteDto){
        restauranteDto.add(linkTo(methodOn(RestauranteController.class)
                .findById(restauranteDto.getId()))
                .withSelfRel());
        restauranteDto.getCozinha().add(linkTo(methodOn(CozinhaController.class)
                .findById(restauranteDto.getCozinha().getId()))
                .withSelfRel());

    }

}

