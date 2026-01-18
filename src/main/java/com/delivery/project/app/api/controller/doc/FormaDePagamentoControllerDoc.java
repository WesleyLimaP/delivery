package com.delivery.project.app.api.controller.doc;


import com.delivery.project.app.api.model.dto.formaDePagamentoDto.request.FormaDePagamentoDescricaoDto;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Formas de Pagamento", description = "Gerencia formas de pagamento")
public interface FormaDePagamentoControllerDoc {

    @Operation(summary = "Lista todas as formas de pagamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de formas de pagamento retornada com sucesso")
    })
    ResponseEntity<List<FormaDePagamentoResponseDto>> findAll();

    @Operation(summary = "Busca uma forma de pagamento por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Forma de pagamento encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada")
    })
    ResponseEntity<FormaDePagamentoResponseDto> findById(@PathVariable Long id);

    @Operation(summary = "Cria uma nova forma de pagamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Forma de pagamento criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<FormaDePagamentoResponseDto> insert(@RequestBody FormaDePagamentoDescricaoDto dto);

    @Operation(summary = "Atualiza uma forma de pagamento existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<FormaDePagamentoResponseDto> update( @RequestBody FormaDePagamentoDescricaoDto dto, @PathVariable Long id);

    @Operation(summary = "Remove uma forma de pagamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Forma de pagamento removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada")
    })
    ResponseEntity<Void> delete(@PathVariable Long id);
}
