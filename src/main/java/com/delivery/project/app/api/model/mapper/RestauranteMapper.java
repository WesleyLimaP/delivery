package com.delivery.project.app.api.model.mapper;
import com.delivery.project.app.api.model.mapper.utils.*;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteInsertDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteUpdateDto;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CozinhaMap.class, FormaDePagamentoMap.class, CidadeMap.class})
public interface RestauranteMapper {

    RestauranteDto toModel(Restaurante restaurante);
    
    @Mapping(target = "cozinha", source = "cozinhaId")
    Restaurante toEntity(RestauranteInsertDto restauranteInsertDto);
    
    void update(@MappingTarget Restaurante restaurante, RestauranteUpdateDto restauranteUpdateDto);
    
    List<RestauranteDto> toCollectionModel(List<Restaurante> restaurantes);
}
