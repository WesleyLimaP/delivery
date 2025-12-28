package com.delivery.project.app.exceptions;

public class AmazonS3StorageException extends StorageException {
    public AmazonS3StorageException(String message) {
        super(message);
    }
}
