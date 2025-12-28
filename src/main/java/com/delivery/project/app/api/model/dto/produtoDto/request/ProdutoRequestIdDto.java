package com.delivery.project.app.api.model.dto.produtoDto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record ProdutoRequestIdDto (@NotNull Long id){
}
