package com.delivery.project.app.domain.events;

import com.delivery.project.app.domain.model.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PedidoCanceladoEvent {
    private Pedido pedido;
    public PedidoCanceladoEvent(Pedido pedido) {
        this.pedido = pedido;
    }
}
