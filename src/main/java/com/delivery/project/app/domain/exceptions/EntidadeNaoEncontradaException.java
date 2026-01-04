package com.delivery.project.app.domain.exceptions;

public abstract class  EntidadeNaoEncontradaException extends RuntimeException{
    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
