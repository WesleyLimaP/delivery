package com.delivery.project.app.domain.listener;

import com.delivery.project.app.domain.events.PedidoConfirmadoEvent;
import com.delivery.project.app.domain.service.TransactionalEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
    @Autowired
    private TransactionalEmailService emailService;

    @TransactionalEventListener
    public void onApplicationEvent(PedidoConfirmadoEvent event) {
        var menssagem = new TransactionalEmailService.Menssagem();
        menssagem.setTo(event.getPedido().getCliente().getEmail());
        menssagem.setSubject("Pedido confirmado");
        menssagem.setTemplate("pedido-confirmado.ftl");
        menssagem.setTemplateData(Map.of("pedido", event.getPedido()));
        emailService.enviarEmail(menssagem);

    }
}
