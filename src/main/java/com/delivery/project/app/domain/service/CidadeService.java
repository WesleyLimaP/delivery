package com.delivery.project.app.domain.service;

import com.delivery.project.app.domain.model.Estado;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.response.CidadeDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeUpdateDto;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.exceptions.CidadeNaoEncontradaException;
import com.delivery.project.app.domain.repository.CidadeRepository;
import com.delivery.project.app.domain.repository.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.delivery.project.app.domain.model.Cidade;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {
    public static final String ID_NAO_ENCONTRADO = " id nao  encontrado";
    public static final String MSG_INTEGRIDADE_REFERENCIAL = "a entidade nao pode ser deletada pois outras classes dependem dela";
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
       Cidade cidade = findByIdOrElseThrow(id);

       return new CidadeDto(cidade);
    }

    @Transactional
    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(MSG_INTEGRIDADE_REFERENCIAL);
        }catch (EntityNotFoundException e){
        throw new CidadeNaoEncontradaException(ID_NAO_ENCONTRADO);
        }
    }



    @Transactional
    public CidadeDto insert(CidadeDto dto){
        Estado estado = estadorepository.findById(dto.getEstadoDto().getId()).orElseThrow(() ->
                new CidadeNaoEncontradaException(ID_NAO_ENCONTRADO));
      Cidade cidade = new Cidade(dto.getNome(), estado );
      dto = new CidadeDto(repository.save(cidade));
      return dto;

    }

    @Transactional
    public CidadeDto update(Long id, CidadeUpdateDto dto){
        Cidade cidade = findByIdOrElseThrow(id);
        cidade.setNome(dto.nome());

        return new CidadeDto(cidade);

    }

    public Cidade findByIdOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new CidadeNaoEncontradaException(ID_NAO_ENCONTRADO));
    }




}
