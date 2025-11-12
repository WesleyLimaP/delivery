package com.delivery.project.app.api.model.dto.formaDePagamentoDto.request;

import jakarta.validation.constraints.NotBlank;

public record FormaDePagamentoRequestDto(@NotBlank String descricao) {
}
