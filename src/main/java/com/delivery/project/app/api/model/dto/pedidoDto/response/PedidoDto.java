package com.delivery.project.app.api.model.dto.pedidoDto.response;

import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteMinDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMinResponse;
import com.delivery.project.app.domain.model.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PedidoDto {
    private Long id;
    private Double subTotal;
    private Double taxaFrete;
    private Double valorTotal;
    private LocalDate dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private UsuarioMinResponse cliente;
    private RestauranteMinDto restaurante;

    public PedidoDto(Pedido x) {
        this.id = x.getId();
        this.subTotal = x.getSubTotal();
        this.taxaFrete = x.getTaxaFrete();
        this.valorTotal = x.getValorTotal();
        this.dataCriacao = x.getDataCriacao();
        this.status = x.getStatus();
        this.cliente = new UsuarioMinResponse(x.getCliente());
        this.restaurante = new RestauranteMinDto(x.getRestaurante());
    }
}
