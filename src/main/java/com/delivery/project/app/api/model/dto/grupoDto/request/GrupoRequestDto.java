package com.delivery.project.app.api.model.dto.grupoDto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record GrupoRequestDto(@NotBlank String nome) {
}
