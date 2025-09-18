package com.delivery.project.app.domain.service;

import com.delivery.project.app.domain.model.Cidade;
import com.delivery.project.app.domain.model.Estado;
import com.delivery.project.app.dto.cidadeDto.CidadeDto;
import com.delivery.project.app.dto.cidadeDto.CidadeUpdateDto;
import com.delivery.project.app.dto.estadoDto.EstadoDto;
import com.delivery.project.app.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.exceptions.IdNaoEncontradoException;
import com.delivery.project.app.repository.CidadeRepository;
import com.delivery.project.app.repository.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository repository;

    @Transactional(readOnly = true)
    public List<EstadoDto> findAll()
    {
        List<Estado> estados =  repository.findAll();
        return estados.stream().map(EstadoDto::new).toList();
    }

    @Transactional(readOnly = true)
    public EstadoDto findById(Long id){
       Estado estado = repository.findById(id).orElseThrow(() ->
                new IdNaoEncontradoException("o id " + id + " nao foi encontrado"));

       return new EstadoDto(estado);
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
    public EstadoDto insert(EstadoDto dto){
      Estado estado = new Estado(dto.getNome(), null);
      dto = new EstadoDto(repository.save(estado));
      return dto;

    }

    @Transactional
    public EstadoDto update(Long id, EstadoDto dto){
        Estado estado = repository.findById(id).orElseThrow(() -> new IdNaoEncontradoException(
                "o id " + id + " nao foi encontrado"));
        estado.setNome(dto.getNome());

        return new EstadoDto(estado);

    }




}
