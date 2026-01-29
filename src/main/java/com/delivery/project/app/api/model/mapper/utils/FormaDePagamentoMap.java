package com.delivery.project.app.api.model.mapper.utils;

import com.delivery.project.app.domain.exceptions.FormaDePagamentoEncontradaException;
import com.delivery.project.app.domain.model.FormaDePagamento;
import com.delivery.project.app.domain.repository.FormaDePagamentoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class FormaDePagamentoMap {

    private final FormaDePagamentoRepository repository;

    FormaDePagamentoMap(FormaDePagamentoRepository repository) {
        this.repository = repository;
    }

    public FormaDePagamento fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new FormaDePagamentoEncontradaException("Produto not found") {
        });
    }

    public List<FormaDePagamento> fromIds(Set<Long> ids) {
        return repository.findAllById(ids);
    }
}
