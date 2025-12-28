package com.delivery.project.app.api.model.dto.pedidoDto.response;

import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.api.model.dto.itemPedidoDto.response.ItemPedidoResponseDto;
import com.delivery.project.app.api.model.dto.endereco.response.EnderecoDto;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteMinDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMinResponse;
import com.delivery.project.app.domain.model.Pedido;
import com.delivery.project.app.domain.model.StatusPedido;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoMaxDto {
    private Long id;
    private Double subTotal;
    private Double taxaFrete;
    private Double valorTotal;
    private LocalDate dataCriacao;
    private LocalDate dataConfirmacao;
    private LocalDate dataCancelamento;
    private LocalDate dataEntrega;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private UsuarioMinResponse cliente;
    private RestauranteMinDto restaurante;
    private EnderecoDto endereco;
    private FormaDePagamentoResponseDto formaDePagamento;
    private  final List<ItemPedidoResponseDto> itens = new ArrayList<>();

    public PedidoMaxDto(Pedido x) {
        this.id = x.getId();
        this.subTotal = x.getSubTotal();
        this.taxaFrete = x.getTaxaFrete();
        this.valorTotal = x.getValorTotal();
        this.dataCriacao = x.getDataCriacao();
        this.dataConfirmacao = x.getDataConfirmacao();
        this.dataCancelamento =  x.getDataCancelamento();
        this.dataEntrega = x.getDataEntrega();
        this.status = x.getStatus();
        this.cliente = new UsuarioMinResponse(x.getCliente());
        this.restaurante = new RestauranteMinDto(x.getRestaurante());
        this.endereco = new EnderecoDto(x.getEndereco());
        this.formaDePagamento = new FormaDePagamentoResponseDto(x.getFormaDePagamento());
        this.itens.addAll(x.getItens().stream().map(ItemPedidoResponseDto::new).toList());
    }
}
