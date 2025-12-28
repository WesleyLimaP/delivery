package com.delivery.project.app.api.model.dto.formaDePagamentoDto.request;

import jakarta.validation.constraints.NotBlank;

public record FormaDePagamentoDescricaoDto(@NotBlank String descricao) {
}
