package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.assembler.PermissaoAssembler;
import com.delivery.project.app.api.model.dto.permissaoDto.PermissaoDto;
import com.delivery.project.app.domain.repository.PermissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissoesService {
    @Autowired
    private PermissoesRepository repository;
    @Autowired
    private PermissaoAssembler assembler;

    @Transactional(readOnly = true)
    public CollectionModel<PermissaoDto> findAll() {
        return assembler.toCollectionModel(repository.findAll());
    }
}
