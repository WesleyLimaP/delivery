package com.delivery.project.app.api.model.dto.usuarioDto.response;

import com.delivery.project.app.domain.model.Usuario;
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
public class UsuarioMaxResponse {
    private Long id;
    private String nome;
    private String email;
    private List<GrupoUsuarioResponseDto> grupos = new ArrayList<>();

    public UsuarioMaxResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.getGrupos().addAll(usuario.getGrupos().stream().map(GrupoUsuarioResponseDto::new).toList());
    }
}
