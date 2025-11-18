package com.delivery.project.app.api.model.dto.usuarioDto.request;

import jakarta.validation.constraints.*;

public record UsuarioUpdateRequestDto( String nome, @Email String email) {


}
