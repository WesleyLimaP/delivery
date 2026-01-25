package com.delivery.project.app.domain.service;
import com.delivery.project.app.api.assembler.CozinhaAssembler;
import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.exceptions.CozinhaNaoEncontradaException;
import com.delivery.project.app.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaService {
    public static final String ID_NAO_ENCONTRADO = "id nao encontrado";
    public static final String MSG_INTEGRIDADE_REFERENCIAL = "A entidade nao pode ser apagada pois existe dependencia com outras classes";
    @Autowired
    private CozinhaRepository repository;
    @Autowired
    private CozinhaAssembler assembler;


    @Transactional(readOnly = true)
    public List<CozinhaDto> findAll() {
       return assembler.toCollectionModel(repository.findAll());
    }


    @Transactional(readOnly = true)
    public CozinhaDto findById(Long id) {
        Cozinha Cozinha = getOrElseThrow(id);
        return assembler.toModel(Cozinha);
    }

    private Cozinha getOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new CozinhaNaoEncontradaException(ID_NAO_ENCONTRADO));
    }

    @Transactional
    public CozinhaDto insert(CozinhaDto dto) {
       var cozinha = assembler.toEntity(dto);
        return assembler.toModel(repository.save(cozinha));
    }


    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(getOrElseThrow(id).getId());
            repository.flush();
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(MSG_INTEGRIDADE_REFERENCIAL);
        }

    }

    @Transactional
    public CozinhaDto update(Long id, CozinhaDto dto) {
        Cozinha cozinha = getOrElseThrow(id);
        assembler.update(cozinha, dto);
        return assembler.toModel(repository.save(cozinha));

    }
    
}
