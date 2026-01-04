package com.delivery.project.app.domain.service;

import lombok.Data;

import java.util.Map;

public interface TransactionalEmailService {
    void enviarEmail(Menssagem menssagem);

    @Data
    class Menssagem {
        private String to;
        private String subject;
        private String template;
        private Map<String, Object> templateData;
    }
}
