package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.model.dto.permissaoDto.PermissaoDto;
import com.delivery.project.app.domain.service.PermissoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {
    @Autowired
    private PermissoesService service;

    @GetMapping
    public CollectionModel<PermissaoDto> findAll(){
        return service.findAll();
    }


}
