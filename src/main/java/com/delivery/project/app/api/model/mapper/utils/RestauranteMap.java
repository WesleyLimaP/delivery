package com.delivery.project.app.api.model.mapper.utils;

import com.delivery.project.app.domain.exceptions.RestauranteNaoEncontradoException;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteMap {
    @Autowired
    private RestauranteRepository repository;

    public Restaurante fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException("Restaurante not found"));
    }
}
