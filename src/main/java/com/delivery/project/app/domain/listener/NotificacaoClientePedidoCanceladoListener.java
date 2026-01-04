package com.delivery.project.app.domain.listener;

import com.delivery.project.app.domain.events.PedidoCanceladoEvent;
import com.delivery.project.app.domain.events.PedidoConfirmadoEvent;
import com.delivery.project.app.domain.service.TransactionalEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

@Component
public class NotificacaoClientePedidoCanceladoListener {
    @Autowired
    private TransactionalEmailService emailService;

    @TransactionalEventListener
    public void onApplicationEvent(PedidoCanceladoEvent event) {
        var menssagem = new TransactionalEmailService.Menssagem();
        menssagem.setTo(event.getPedido().getCliente().getEmail());
        menssagem.setSubject("Pedido cancelado");
        menssagem.setTemplate("pedido-cancelado.ftl");
        menssagem.setTemplateData(Map.of("pedido", event.getPedido()));
        emailService.enviarEmail(menssagem);

    }
}
