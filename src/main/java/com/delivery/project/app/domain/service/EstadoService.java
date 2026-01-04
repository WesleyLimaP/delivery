package com.delivery.project.app.domain.service;

import com.delivery.project.app.domain.model.Estado;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.exceptions.EstadoNaoEncontradoException;
import com.delivery.project.app.domain.repository.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {
    public static final String ID_NAO_FOI_ENCONTRADO = "id nao foi encontrado";
    public static final String MSG_INTEGRIDADE_REFERENCIAL = "a entidade nao pode ser deletada pois outras classes dependem dela";
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
       Estado estado = getOrElseThrow(id);

       return new EstadoDto(estado);
    }

    @Transactional
    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(MSG_INTEGRIDADE_REFERENCIAL);
        }catch (EntityNotFoundException e){
        throw new EstadoNaoEncontradoException(ID_NAO_FOI_ENCONTRADO);
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
        Estado estado = getOrElseThrow(id);
        estado.setNome(dto.getNome());

        return new EstadoDto(estado);

    }

    private Estado getOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(
                ID_NAO_FOI_ENCONTRADO));
    }


}
