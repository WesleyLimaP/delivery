package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.assembler.FormaDePagamentoAssembler;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.request.FormaDePagamentoDescricaoDto;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.domain.model.FormaDePagamento;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.exceptions.FormaDePagamentoEncontradaException;
import com.delivery.project.app.domain.repository.FormaDePagamentoRepository;
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
    @Autowired
    private FormaDePagamentoAssembler assembler;

    @Transactional(readOnly = true)
    public List<FormaDePagamentoResponseDto> getAll() {
        var formasDePagamento = repository.findAll();
        return assembler.toCollectionModel(formasDePagamento);
    }

    @Transactional(readOnly = true)
    public FormaDePagamentoResponseDto getById(@PathVariable Long id) {
        FormaDePagamento formaDePagamento = getOrElseThrow(id);
        return assembler.toModel(formaDePagamento);
    }

    private FormaDePagamento getOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new FormaDePagamentoEncontradaException(RESTAURANTE_NAO_ENCONTRADO_MSG));
    }

    @Transactional
    public FormaDePagamentoResponseDto insert(FormaDePagamentoDescricaoDto dto) {
        var formaDePagamento = assembler.toEntity(dto);
        formaDePagamento = repository.save(formaDePagamento);
        return assembler.toModel(formaDePagamento);
    }

    @Transactional
    public FormaDePagamentoResponseDto update(FormaDePagamentoDescricaoDto dto, Long id) {
        FormaDePagamento formaDePagamento = getOrElseThrow(id);
        assembler.update(formaDePagamento, dto);
        return assembler.toModel(formaDePagamento);
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
