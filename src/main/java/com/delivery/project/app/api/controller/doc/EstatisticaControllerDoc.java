package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.estatisticaDto.EstatisticaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Estatísticas", description = "crud de Estatísticas")
public interface EstatisticaControllerDoc {

    @Operation(summary = "Obtém estatísticas de pedidos por data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estatísticas retornadas com sucesso"),
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @GetMapping
    ResponseEntity<List<EstatisticaDto>> gerarRelatorioPedidosPorData(
        @RequestParam(required = false) Long restauranteId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
        @RequestParam(required = false) LocalDate dataFim
    );

    @Operation(summary = "Gera relatório de estatísticas em PDF",
              description = "Gera e retorna um relatório em PDF com estatísticas de pedidos, podendo ser filtrado por restaurante e período")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Relatório PDF gerado com sucesso",
                   content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/pdf")),
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @GetMapping(value = "/total", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<byte[]> gerarRelatorioPedidosPorDataPdf(
        @RequestParam(required = false) Long restauranteId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
        @RequestParam(required = false) LocalDate dataFim
    ) throws JRException;
}
