package com.delivery.project.app.api.model.dto.usuarioDto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostRequestDto {
    private String nome;
    @NotBlank
    @Max(20)
    @Min(6)
    private String senha;
    @NotNull
    private List<GrupoUsuarioRequestDto> grupos = new ArrayList<>();
}
