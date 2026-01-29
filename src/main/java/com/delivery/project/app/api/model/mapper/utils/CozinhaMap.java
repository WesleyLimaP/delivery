package com.delivery.project.app.api.model.mapper.utils;

import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;

@Component
public class CozinhaMap {
    private final CozinhaRepository repository;

    CozinhaMap(CozinhaRepository repository) {
        this.repository = repository;
    }

    public Cozinha fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cozinha nao encontrada"));
    }
}
