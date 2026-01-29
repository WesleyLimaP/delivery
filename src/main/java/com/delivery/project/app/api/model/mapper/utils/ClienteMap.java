package com.delivery.project.app.api.model.mapper.utils;

import com.delivery.project.app.domain.exceptions.RestauranteNaoEncontradoException;
import com.delivery.project.app.domain.model.Usuario;
import com.delivery.project.app.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMap {
    @Autowired
    private UsuarioRepository repository;

    public Usuario fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException("Produto not found") {
        });
    }
}
