package com.delivery.project.app.api.handler;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


public enum ProblemType {
    ERRO_DE_CAMPO("erro-de-campo", "erro de campo"),
    ERRO_DE_SISTEMA("erro-interno", "erro interno"),
    ENTIDADE_NAO_ENCONTRADA("enridade-nao-encontrada", "entidade nao encontrada"),
    ERRO_SINTATICO("erro-sintatico", "body do json esta incorreto"),
    PARAMETRO_INVALIDO("parametro-invalido", "parametro de recurso invalido " ),
    RECURSO_NAO_ENCONTRADO("recurso-invalido", "recurso invalido " ),
    ENTIDADE_EM_USO("entidade-em-uso", "entidade em uso"),
    ERRO_DE_ASSOCIACAO("erro-de-associacao", "erro de asssociação");

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
