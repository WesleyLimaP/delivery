package com.delivery.project.app.domain.events;

import com.delivery.project.app.domain.model.Pedido;
import com.delivery.project.app.domain.repository.PedidoRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@Getter
@Setter
public class PedidoConfirmadoEvent {
    private Pedido pedido;
    public PedidoConfirmadoEvent(Pedido pedido) {
        this.pedido = pedido;
    }
}
