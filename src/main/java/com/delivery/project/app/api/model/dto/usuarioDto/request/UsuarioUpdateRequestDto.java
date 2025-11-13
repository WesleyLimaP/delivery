package com.delivery.project.app.api.model.dto.usuarioDto.request;

import jakarta.validation.constraints.*;

public record UsuarioUpdateRequestDto(@Email String nome) {


}
