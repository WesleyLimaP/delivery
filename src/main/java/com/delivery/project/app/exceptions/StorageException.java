package com.delivery.project.app.exceptions;

public abstract class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }
}
