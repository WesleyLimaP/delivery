package com.delivery.project.app.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
    CRIADO("criado"),
    CONFIRMADO("confirmado", CRIADO),
    ENTREGUE("entregue", CONFIRMADO),
    CANCELADO("cancelado", CONFIRMADO, CRIADO);

    private final String descricao;
    final List<StatusPedido> statusAnterior;

    StatusPedido(String descricao, StatusPedido... statausAnterior ) {
        this.descricao = descricao;
        this.statusAnterior = Arrays.asList(statausAnterior);
    }

    public String getDescricao() {
        return descricao;
    }

    public List<StatusPedido> getStatusAnterior() {
        return statusAnterior;
    }
}
