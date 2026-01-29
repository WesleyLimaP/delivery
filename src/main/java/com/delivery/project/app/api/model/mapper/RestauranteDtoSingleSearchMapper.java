package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDtoSingleSearch;
import com.delivery.project.app.domain.model.Restaurante;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteDtoSingleSearchMapper {
    
    RestauranteDtoSingleSearch toModel(Restaurante restaurante);
    List<RestauranteDtoSingleSearch> toCollectionModel(List<Restaurante> restaurantes);
}
