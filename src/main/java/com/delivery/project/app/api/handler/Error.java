package com.delivery.project.app.api.handler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class Error {
    private ProblemType errorType;
    private Integer status;
    private String detalhe;
    private String tittle;
    private String userMessage;
    private LocalDateTime timestamp;
    private Map<String, String> fieldErrors;

}
