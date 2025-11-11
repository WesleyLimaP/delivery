package com.delivery.project.app.api.model.dto.restauranteDto;

import jakarta.validation.constraints.NotBlank;

public record FormaDePagamentoDto(Long id, @NotBlank String nome) {
}
