package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.EstatisticaControllerDoc;
import com.delivery.project.app.api.model.dto.estatisticaDto.EstatisticaDto;
import com.delivery.project.app.domain.service.EstatisticaService;
import com.delivery.project.app.domain.service.VendasReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/estatisticas")
@Tag(name = "Estatísticas", description = "crud de Estatísticas")
public class EstatisticaController implements EstatisticaControllerDoc {
    @Autowired
    private EstatisticaService service;
    @Autowired
    private VendasReportService reportService;


    public ResponseEntity<List<EstatisticaDto>> gerarRelatorioPedidosPorData(@RequestParam(required = false) Long restauranteId,
                                                                      @RequestParam(required = false)
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      LocalDate dataInicio,
                                                                      @RequestParam(required = false) LocalDate dataFim){
        return ResponseEntity.ok().body(service.getTotalByDate(restauranteId, dataInicio, dataFim));

    }
    @GetMapping(value = "/total", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> gerarRelatorioPedidosPorDataPdf(@RequestParam(required = false) Long restauranteId,
                                                             @RequestParam(required = false)
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      LocalDate dataInicio,
                                                             @RequestParam(required = false) LocalDate dataFim) throws JRException {

        byte[] bytes = reportService.emitirVendas(restauranteId, dataInicio, dataFim);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "Attachment; Filename = vendas-diarias.pdf");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytes);

    }
}
