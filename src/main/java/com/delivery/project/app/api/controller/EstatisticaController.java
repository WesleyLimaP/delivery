package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.model.dto.estatisticaDto.EstatisticaDto;
import com.delivery.project.app.domain.service.EstatisticaService;
import com.delivery.project.app.domain.service.VendasReportService;
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
public class EstatisticaController {
    @Autowired
    private EstatisticaService service;
    @Autowired
    private VendasReportService reportService;

    @GetMapping(value = "/total", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstatisticaDto>> getTotalPedidosByDate(@RequestParam(required = false) Long restauranteId,
                                                                      @RequestParam(required = false)
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      LocalDate dataInicio,
                                                                      @RequestParam(required = false) LocalDate dataFim){
        return ResponseEntity.ok().body(service.getTotalByDate(restauranteId, dataInicio, dataFim));

    }
    @GetMapping(value = "/total", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getTotalPedidosByDatePdf  (@RequestParam(required = false) Long restauranteId,
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
