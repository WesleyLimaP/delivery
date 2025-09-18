package com.delivery.project.app.domain.service;
import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.dto.cozinhaDto.CozinhaDto;
import com.delivery.project.app.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.exceptions.IdNaoEncontradoException;
import com.delivery.project.app.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaService {
    @Autowired
    private CozinhaRepository repository;


    @Transactional(readOnly = true)
    public List<CozinhaDto> findAll() {
        return repository.findAll().stream().map(CozinhaDto::new).toList();
    }


    @Transactional(readOnly = true)
    public CozinhaDto findById(Long id) {
        Cozinha Cozinha = repository.findById(id).orElseThrow(() ->
                new IdNaoEncontradoException("id nao encontrado"));

        return new CozinhaDto(Cozinha);
    }

    @Transactional
    public CozinhaDto insert(CozinhaDto dto) {
        Cozinha cozinha = new Cozinha();
        marge(cozinha, dto);
        return new CozinhaDto(repository.save(cozinha));
    }

    private static void marge(Cozinha cozinha, CozinhaDto dto) {
        cozinha.setNome(dto.getNome());
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById( repository.findById(id).orElseThrow(() ->
                    new IdNaoEncontradoException("Cozinha nao encontrado")).getId());
            repository.flush();
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException("A entidade nao pode ser apagada pois existe dependencia com outras classes" );
        }

    }

    @Transactional
    public CozinhaDto update(Long id, CozinhaDto dto) {
        Cozinha cozinha = repository.findById(id).orElseThrow(() ->
                new IdNaoEncontradoException("Cozinha com id " + id + " nao foi encontrado"));

        marge(cozinha, dto);
        return new CozinhaDto(cozinha);

    }
    
}
