package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.pedidoDto.request.PedidoRequestDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoMaxDto;
import com.delivery.project.app.api.model.filter.PedidoFilter;
import com.delivery.project.app.domain.model.Pedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pedidos", description = "Gerencia pedidos")
public interface PedidoControllerDoc {

    @Operation(summary = "Lista todos os pedidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso")
    })
    ResponseEntity<Page<List<PedidoDto>>> findAll(PedidoFilter filter, Pageable pageable);

    @Operation(summary = "Busca um pedido por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    ResponseEntity<PedidoMaxDto> findById(@PathVariable Long id);

    @Operation(summary = "Cria um novo pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<PedidoMaxDto> insert(@RequestBody PedidoRequestDto pedido);

    @Operation(summary = "Atualiza o status do pedido para CONFIRMADO")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Status do pedido atualizado para CONFIRMADO"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    ResponseEntity<Void> confirmarPedido(@PathVariable Long id);

    @Operation(summary = "Atualiza o status do pedido para ENTREGUE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Status do pedido atualizado para ENTREGUE"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    ResponseEntity<Void> entregarPedido(@PathVariable Long id);

    @Operation(summary = "Atualiza o status do pedido para CANCELADO")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Status do pedido atualizado para CANCELADO"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    ResponseEntity<Void> cancelarPedido(@PathVariable Long id);
}
