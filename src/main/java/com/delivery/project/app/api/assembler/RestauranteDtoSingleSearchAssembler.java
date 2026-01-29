package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.*;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDtoSingleSearch;
import com.delivery.project.app.api.model.mapper.RestauranteDtoSingleSearchMapper;
import com.delivery.project.app.api.model.mapper.RestauranteMapper;
import com.delivery.project.app.domain.model.Restaurante;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDslKt.withRel;

@Mapper(componentModel = "spring")
public  class RestauranteDtoSingleSearchAssembler implements RepresentationModelAssembler<Restaurante, RestauranteDtoSingleSearch> {

    @Autowired
    private RestauranteDtoSingleSearchMapper restauranteMapper;

    @Override
    public RestauranteDtoSingleSearch toModel(Restaurante restaurante){
        var restauranteDtoSingleSearch = restauranteMapper.toModel(restaurante);
        addLinks(restauranteDtoSingleSearch);
        return restauranteDtoSingleSearch;
    };

    private void addLinks(@MappingTarget RestauranteDtoSingleSearch restauranteDtoSingleSearch){
        restauranteDtoSingleSearch.add(linkTo(methodOn(RestauranteController.class)
                .findById(restauranteDtoSingleSearch.getId()))
                .withSelfRel());
        restauranteDtoSingleSearch.getCozinha()
                .add(linkTo(methodOn(CozinhaController.class)
                .findById(restauranteDtoSingleSearch.getCozinha().getId()))
                .withRel("cozinha"));
        restauranteDtoSingleSearch.getEndereco().getCidade()
                .add(linkTo(methodOn(CidadeController.class)
                .findById(restauranteDtoSingleSearch.getEndereco().getCidade().getId()))
                .withRel("cidade"));
        restauranteDtoSingleSearch.getEndereco().getCidade().getEstado()
                .add(linkTo(methodOn(EstadoController.class)
                .findById(restauranteDtoSingleSearch.getEndereco().getCidade().getEstado().getId()))
                .withRel("estado"));
        restauranteDtoSingleSearch.getFormasPagamento()
                .forEach(x -> x.add(
                    linkTo(methodOn(FormaDePagamentoController.class)
                    .findById(x.getId()))
                            .withSelfRel()
                ));

    }
}
