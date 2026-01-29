package com.delivery.project.app.api.model.mapper.utils;

import com.delivery.project.app.domain.exceptions.CidadeNaoEncontradaException;
import com.delivery.project.app.domain.model.Cidade;
import com.delivery.project.app.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;

@Component
public class CidadeMap {
    private final CidadeRepository repository;

    CidadeMap(CidadeRepository repository) {
        this.repository = repository;
    }

    public Cidade fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException("cidade not found") {
        });
    }
}
