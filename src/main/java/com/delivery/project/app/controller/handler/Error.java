package com.delivery.project.app.controller.handler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Error {
    private LocalDateTime data;
    private String meensagem;
}
