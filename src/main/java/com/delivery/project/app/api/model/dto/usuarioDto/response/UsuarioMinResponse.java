package com.delivery.project.app.api.model.dto.usuarioDto.response;

import com.delivery.project.app.domain.model.Usuario;

public record UsuarioMinResponse (Long id, String nome){
    public UsuarioMinResponse(Usuario usuario){
        this(usuario.getId(), usuario.getNome());
    }


}
