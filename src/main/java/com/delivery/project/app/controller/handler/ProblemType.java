package com.delivery.project.app.controller.handler;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


public enum ProblemType {
    ERRO_DE_SISTEMA("erro-interno", "erro interno"),
    ENTIDADE_NAO_ENCONTRADA("enridade-nao-encontrada", "entidade nao encontrada"),
    ERRO_SINTATICO("erro-sintatico", "body do json esta incorreto"),
    PARAMETRO_INVALIDO("parametro-invalido", "parametro de recurso invalido " ),
    RECURSO_NAO_ENCONTRADO("recurso-invalido", "recurso invalido " );

    String uri = "LocalHost:8080//";
    @Getter
    String title;

    ProblemType(String path, String title) {
        this.uri += path;
        this.title = title;
    }

    @JsonValue
    public String getUri() {
        return uri;
    }
}
