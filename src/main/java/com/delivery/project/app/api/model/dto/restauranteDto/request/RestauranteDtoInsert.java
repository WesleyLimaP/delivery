package com.delivery.project.app.api.model.dto.restauranteDto.request;

import com.delivery.project.app.domain.model.Endereco;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class RestauranteDtoInsert {
    @NotBlank
    private String nome;
    @NotNull
    private boolean aberto;
    @NotNull
    @DecimalMin("0")
    private Double taxaFrete;
    @NotNull
    private Long cozinhaId;
    @Setter(AccessLevel.NONE)
    @NotNull
    private List<Long> formasDePagamento;
    @NotNull
    private Endereco endereco;

}
