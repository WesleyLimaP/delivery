package com.delivery.project.app.api.model.mapper.utils;

import com.delivery.project.app.domain.exceptions.EstadoNaoEncontradoException;
import com.delivery.project.app.domain.model.Estado;
import com.delivery.project.app.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoMap {
    @Autowired
    private EstadoRepository repository;

    public Estado fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException("Estado nao encontrado"));
    }

}
