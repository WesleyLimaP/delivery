package com.delivery.project.app.controller.handler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Error {
    private ProblemType errorType;
    private Integer status;
    private String detalhe;
    private String tittle;
    private String userMessage;
    private LocalDateTime timestamp;

}
