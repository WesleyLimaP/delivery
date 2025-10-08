package com.delivery.project.app.exceptions;

public abstract class  EntidadeNaoEncontradaException extends RuntimeException{
    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
