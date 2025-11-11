package com.delivery.project.app.api.model.dto.restauranteDto;

import com.delivery.project.app.domain.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestauranteDtoInsert {
    @NotBlank
    private String nome;
    @NotNull
    @DecimalMin("1")
    private Double taxaFrete;
    @NotNull
    private Long cozinhaId;
    @Setter(AccessLevel.NONE)
    @NotNull
    private List<Long> formasDePagamento;
    @NotNull
    private Endereco endereco;

}
