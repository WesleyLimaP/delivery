package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.CozinhaController;
import com.delivery.project.app.api.controller.RestauranteController;
import com.delivery.project.app.api.controller.UsuarioController;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteInsertDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteUpdateDto;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.domain.repository.CozinhaRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", uses = {CozinhaMap.class, FormaDePagamentoMap.class, CidadeMap.class})
public interface RestauranteAssembler extends RepresentationModelAssembler<Restaurante, RestauranteDto> {

    @Override
    RestauranteDto toModel(Restaurante restaurante);
    @Mapping(target = "cozinha", source = "cozinhaId")
    @Mapping(target = "formasPagamento", source = "formasDePagamento")
    @Mapping(target = "endereco.cidade", source = "endereco.cidade.id")
    Restaurante toEntity(RestauranteInsertDto dto);
    List<RestauranteDto> toCollectionModel(List<? extends Restaurante> entities);
    @Mapping(target = "cozinha", source = "cozinhaId")
    @Mapping(target = "endereco.cidade", source = "endereco.cidade.id")
    void update(@MappingTarget Restaurante restaurante, RestauranteUpdateDto dto);

    @AfterMapping
    default void addLinks(@MappingTarget RestauranteDto restauranteDto){
        restauranteDto.add(linkTo(methodOn(RestauranteController.class)
                .findById(restauranteDto.getId()))
                .withSelfRel());
        restauranteDto.getCozinha().add(linkTo(methodOn(CozinhaController.class)
                .findById(restauranteDto.getCozinha().getId()))
                .withSelfRel());

    }

}
@Component
class CozinhaMap {
    private final CozinhaRepository repository;

    CozinhaMap(CozinhaRepository repository) {
        this.repository = repository;
    }

    public Cozinha fromId(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cozinha nao encontrada"));
    }
}

