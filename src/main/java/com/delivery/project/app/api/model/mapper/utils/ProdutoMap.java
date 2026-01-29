package com.delivery.project.app.api.model.mapper.utils;

import com.delivery.project.app.domain.exceptions.ProdutoNaoEncontradoException;
import com.delivery.project.app.domain.model.Produto;
import com.delivery.project.app.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMap {
    @Autowired
    private ProdutoRepository repository;

    public Produto fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto not found") {
        });
    }
}
