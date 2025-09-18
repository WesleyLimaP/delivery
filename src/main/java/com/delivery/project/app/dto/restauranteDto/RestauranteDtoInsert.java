package com.delivery.project.app.dto.restauranteDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
public class RestauranteDtoInsert {
    private String nome;
    private Double taxaFrete;
    private Long cozinhaId;
    @Setter(AccessLevel.NONE)
    private final List<Long> formasDePagamento;

}
