package com.delivery.project.app.domain.service.util.mapper.restaurante;

import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteDtoInsert;
import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.domain.model.FormaDePagamento;
import com.delivery.project.app.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteMapper {
    public void toEntity(Restaurante restaurante, RestauranteDtoInsert dto, Cozinha cozinha, List<FormaDePagamento> formaDePagamentosObj) {
        restaurante.setCozinha(cozinha);
        restaurante.setNome(dto.getNome());
        restaurante.setTaxaFrete(dto.getTaxaFrete());
        restaurante.setAberto(dto.isAberto());
        restaurante.getFormasPagamento().addAll(formaDePagamentosObj);
        restaurante.setEndereco(dto.getEndereco());
    }
}
