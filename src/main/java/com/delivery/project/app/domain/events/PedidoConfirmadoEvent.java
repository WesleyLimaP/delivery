package com.delivery.project.app.domain.events;

import com.delivery.project.app.domain.model.Pedido;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class PedidoConfirmadoEvent {
    private Pedido pedido;
    public PedidoConfirmadoEvent(Pedido pedido) {
        this.pedido = pedido;
    }
}
