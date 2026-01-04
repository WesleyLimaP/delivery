package com.delivery.project.app.domain.exceptions;

public class AmazonS3StorageException extends StorageException {
    public AmazonS3StorageException(String message) {
        super(message);
    }
}
