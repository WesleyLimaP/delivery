package com.delivery.project.app.api.model.dto.restauranteDto.request;

import com.delivery.project.app.api.model.dto.endereco.request.EnderecoRequestDto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestauranteInsertDto {
    @NotBlank
    private String nome;
    @NotNull
    @DecimalMin("0")
    private Double taxaFrete;
    @NotNull
    private Long cozinhaId;
    @NotNull
    private Set<Long> formasDePagamento;
    @NotNull
    private EnderecoRequestDto endereco;

}
