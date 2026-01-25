package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurante Formas de Pagamento", description = "Gerencia as formas de pagamento de um restaurante")
public interface RestauranteFormaDePagamentoControllerDoc {

    @Operation(summary = "Lista todas as formas de pagamento de um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de formas de pagamento retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    ResponseEntity<List<FormaDePagamentoResponseDto>> findById(@PathVariable Long restauranteId);

    @Operation(summary = "Associa uma forma de pagamento a um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Forma de pagamento associada ao restaurante com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrada")
    })
    ResponseEntity<List<FormaDePagamentoResponseDto>> associar(
        @PathVariable Long restauranteId,
        @PathVariable Long formaPagamentoId
    );

    @Operation(summary = "Remove a associação de uma forma de pagamento de um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Forma de pagamento removida do restaurante com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrada")
    })
    ResponseEntity<Void> desassociar(
        @PathVariable Long restauranteId,
        @PathVariable Long formaPagamentoId
    );
}
