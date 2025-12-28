package com.delivery.project.app.domain.service;

import net.sf.jasperreports.engine.JRException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Component
public interface VendasReportService {
    byte []  emitirVendas(Long restauranteId,
                          LocalDate dataInicio,
                          LocalDate dataFim) throws JRException;
}
