package com.delivery.project.app.infra.service;

import com.delivery.project.app.domain.repository.EstatisticaRepository;
import com.delivery.project.app.domain.service.VendasReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class  VendasReportPdfServiceImpl implements VendasReportService {
    @Autowired
    private EstatisticaRepository repository;

    @Override
    public byte[] emitirVendas(Long restauranteId, LocalDate dataInicio, LocalDate dataFim) throws JRException {
        var inputStream = this.getClass().getResourceAsStream("/reports/vendas-diarias.jasper");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("tipo", JasperReport.PROPERTY_SECTION_TYPE);
        var vendasDiarias = repository.getTotal(restauranteId, dataInicio, dataFim);
        var dataSource = new JRBeanCollectionDataSource(vendasDiarias);
        var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
