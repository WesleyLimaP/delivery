package com.delivery.project.app.api.model.dto.produtoDto.request;

import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoRequestDto extends RepresentationModel<ProdutoRequestDto> {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    @Positive
    private Double preco;
    @NotNull
    private boolean ativo;
}
