package com.delivery.project.app.domain.service;

import com.delivery.project.app.domain.model.Estado;
import com.delivery.project.app.dto.cidadeDto.CidadeDto;
import com.delivery.project.app.dto.cidadeDto.CidadeUpdateDto;
import com.delivery.project.app.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.exceptions.IdNaoEncontradoException;
import com.delivery.project.app.repository.CidadeRepository;
import com.delivery.project.app.repository.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.delivery.project.app.domain.model.Cidade;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository repository;
    @Autowired
    private EstadoRepository estadorepository;

    @Transactional(readOnly = true)
    public List<CidadeDto> findAll()
    {
        List<Cidade> cidade =  repository.findAll();
        return cidade.stream().map(CidadeDto::new).toList();
    }

    @Transactional(readOnly = true)
    public CidadeDto findById(Long id){
       Cidade cidade = repository.findById(id).orElseThrow(() ->
                new IdNaoEncontradoException("o id " + id + " nao foi encontrado"));

       return new CidadeDto(cidade);
    }

    @Transactional
    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException("a entidade nao pode ser deletada pois outras classes dependem dela");
        }catch (EntityNotFoundException e){
        throw new IdNaoEncontradoException("o id " + id + " nao foi encontrado");
        }
    }



    @Transactional
    public CidadeDto insert(CidadeDto dto){
        Estado estado = estadorepository.findById(dto.getEstadoDto().getId()).orElseThrow(() ->
                new IdNaoEncontradoException("o id nao foi encontrado"));
      Cidade cidade = new Cidade(null, dto.getNome(), estado );
      dto = new CidadeDto(repository.save(cidade));
      return dto;

    }

    @Transactional
    public CidadeDto update(Long id, CidadeUpdateDto dto){
        Cidade cidade = repository.findById(id).orElseThrow(() -> new IdNaoEncontradoException(
                "o id " + id + " nao foi encontrado"));
        cidade.setNome(dto.nome());

        return new CidadeDto(cidade);

    }




}
