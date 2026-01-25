package com.delivery.project.app.api.model.dto.usuarioDto.response;

import com.delivery.project.app.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioMaxResponse  extends RepresentationModel<UsuarioMaxResponse> {
    private Long id;
    private String nome;
    private String email;
    private List<GrupoUsuarioResponseDto> grupos;
}
