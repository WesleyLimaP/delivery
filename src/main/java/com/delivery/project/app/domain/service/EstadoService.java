package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.assembler.EstadoAssembler;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.request.EstadoNomeDto;
import com.delivery.project.app.domain.model.Estado;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.exceptions.EstadoNaoEncontradoException;
import com.delivery.project.app.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {
    public static final String ID_NAO_FOI_ENCONTRADO = "id nao foi encontrado";
    public static final String MSG_INTEGRIDADE_REFERENCIAL = "a entidade nao pode ser deletada pois outras classes dependem dela";
    @Autowired
    private EstadoRepository repository;
    @Autowired
    private EstadoAssembler assembler;

    @Transactional(readOnly = true)
    public CollectionModel<EstadoDto> findAll()
    {
        List<Estado> estados =  repository.findAll();
        return assembler.toCollectionModel(estados);
    }

    @Transactional(readOnly = true)
    public EstadoDto findById(Long id){
       Estado estado = getOrElseThrow(id);
       return assembler.toModel(estado);
    }

    @Transactional
    public void delete(Long id){
        try {
            var estado = getOrElseThrow(id);
            repository.delete(estado);
            repository.flush();
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(MSG_INTEGRIDADE_REFERENCIAL);
        }


    }


    @Transactional
    public EstadoDto insert(EstadoDto dto){
      Estado estado = assembler.toEntity(dto);
      return assembler.toModel(repository.save(estado));
    }

    @Transactional
    public EstadoDto update(Long id, EstadoNomeDto dto){
        Estado estado = getOrElseThrow(id);
        estado.setNome(dto.nome());
        assembler.update(estado, dto);
        return assembler.toModel(estado);

    }

    private Estado getOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(
                ID_NAO_FOI_ENCONTRADO));
    }


}
