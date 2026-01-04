package com.delivery.project.app.domain.exceptions;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public ProdutoNaoEncontradoException(String msg) {
        super(msg);
    }
}
