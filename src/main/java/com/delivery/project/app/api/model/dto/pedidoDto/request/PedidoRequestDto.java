package com.delivery.project.app.api.model.dto.pedidoDto.request;

import com.delivery.project.app.api.model.dto.endereco.request.EnderecoRequestDto;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.request.FormaDePagamentoIdDto;
import com.delivery.project.app.api.model.dto.itemPedidoDto.request.ItemPedidoRequestDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteIdDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioIdDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoRequestDto {
        @NotNull
        private UsuarioIdDto cliente;
        @NotNull
        private RestauranteIdDto restaurante;
        @NotNull
        private EnderecoRequestDto endereco;
        @NotNull
        private FormaDePagamentoIdDto formaDePagamento;
        @NotNull
        private  List<ItemPedidoRequestDto> itens = new ArrayList<>();


}
