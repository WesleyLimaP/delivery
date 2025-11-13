package com.delivery.project.app.api.model.dto.usuarioDto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UsuarioUpdateSenhaDto (@NotBlank @Max(20) @Min(6) String senha){
}
