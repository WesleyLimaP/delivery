package com.delivery.project.app.api.model.dto.endereco.cidadeDto.request;

import jakarta.validation.constraints.NotBlank;

public record CidadeUpdateDto(@NotBlank String nome) {
}
