package com.delivery.project.app.api.model.dto.cidadeDto;

import jakarta.validation.constraints.NotBlank;

public record CidadeUpdateDto(@NotBlank String nome) {
}
