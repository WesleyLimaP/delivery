package com.delivery.project.app.dto.restauranteDto;

import com.delivery.project.app.domain.model.Endereco;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestauranteDtoInsert {
    private String nome;
    private Double taxaFrete;
    private Long cozinhaId;
    @Setter(AccessLevel.NONE)
    private final List<Long> formasDePagamento = new ArrayList<>();
    private Endereco endereco;

}
