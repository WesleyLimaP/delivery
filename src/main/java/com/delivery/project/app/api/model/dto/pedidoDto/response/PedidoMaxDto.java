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
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoMaxDto extends RepresentationModel<PedidoMaxDto> {
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

}
