package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.model.dto.formaDePagamentoDto.request.FormaDePagamentoRequestDto;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.domain.model.FormaDePagamento;
import com.delivery.project.app.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.exceptions.FormaDePagamentoEncontradaException;
import com.delivery.project.app.repository.FormaDePagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class FormaDePagamentoService {
    public static final String RESTAURANTE_NAO_ENCONTRADO_MSG = "restaurante nao encontrado";
    public static final String INTEGRIDADE_REFERENCIAL_MSG = "A entidade nao pode ser apagada pois existe dependencia com outras classes";
    @Autowired
    private FormaDePagamentoRepository repository;

    @Transactional(readOnly = true)
    public List<FormaDePagamentoResponseDto> getAll() {
        List<FormaDePagamento>  formaDePagamentos = repository.findAll();
        return formaDePagamentos.stream().map(FormaDePagamentoResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public FormaDePagamentoResponseDto getById(@PathVariable Long id) {
        FormaDePagamento formaDePagamento = getOrElseThrow(id);
        return new FormaDePagamentoResponseDto(formaDePagamento);
    }

    private FormaDePagamento getOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new FormaDePagamentoEncontradaException(RESTAURANTE_NAO_ENCONTRADO_MSG));
    }

    @Transactional
    public FormaDePagamentoResponseDto insert(FormaDePagamentoRequestDto dto) {
        FormaDePagamento formaDePagamento = new FormaDePagamento();
        margeDtoToEntity(dto, formaDePagamento);

        formaDePagamento = repository.save(formaDePagamento);
        return new FormaDePagamentoResponseDto(formaDePagamento);
    }
    private void margeDtoToEntity(FormaDePagamentoRequestDto dto, FormaDePagamento formaDePagamento){
        formaDePagamento.setDescricao(dto.descricao());
    }
    @Transactional
    public FormaDePagamentoResponseDto update(FormaDePagamentoRequestDto dto, Long id) {
        FormaDePagamento formaDePagamento = getOrElseThrow(id);
        margeDtoToEntity(dto, formaDePagamento);
        return new FormaDePagamentoResponseDto(formaDePagamento);
    }

    @Transactional
    public void delete(Long id) {
        try {
            FormaDePagamento formaDePagamento = getOrElseThrow(id);
            repository.delete(formaDePagamento);
            repository.flush();
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(INTEGRIDADE_REFERENCIAL_MSG);
        }
    }
}
