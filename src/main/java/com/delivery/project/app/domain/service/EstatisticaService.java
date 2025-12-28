package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.model.dto.estatisticaDto.EstatisticaDto;
import com.delivery.project.app.domain.repository.EstatisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstatisticaService {
    @Autowired
    private EstatisticaRepository repository;


    public List<EstatisticaDto> getTotalByDate (Long restauranteId, LocalDate dataInicio, LocalDate dataFim ){
        return repository.getTotal(restauranteId, dataInicio, dataFim).stream().map(EstatisticaDto::new).toList();
    }

}
