package com.delivery.project.app.api.controller;

import com.delivery.project.app.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoPermissaoController {
    @Autowired
    private GrupoService service;



    @GetMapping(value = "/{grupoId}/permissoes")
    public ResponseEntity<?> findAll(@PathVariable Long grupoId){
        return ResponseEntity.ok().body(service.findAllPermissoes(grupoId));
    }
    @GetMapping(value = "/{grupoId}/permissoes/{permissaoId}")
    public ResponseEntity<?> findAll(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        return ResponseEntity.ok().body(service.findByIdPermissoes(grupoId, permissaoId));
    }
    @PutMapping(value = "/{grupoId}/permissoes/{permissaoId}")
    public ResponseEntity<?> associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        return ResponseEntity.ok().body(service.associarPermissao(grupoId, permissaoId));
    }
    @DeleteMapping(value = "/{grupoId}/permissoes/{permissaoId}")
    public ResponseEntity<?> desassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        return ResponseEntity.ok().body(service.desassociarPermissao(grupoId, permissaoId));
    }

}
