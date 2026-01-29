package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.GrupoPermissaoControllerDoc;
import com.delivery.project.app.api.model.dto.permissaoDto.PermissaoDto;
import com.delivery.project.app.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoPermissaoController  implements GrupoPermissaoControllerDoc {
    @Autowired
    private GrupoService service;



    @GetMapping(value = "/{grupoId}/permissoes")
    public CollectionModel<PermissaoDto> findAllPermissoes(@PathVariable Long grupoId){
        return service.findAllPermissoes(grupoId);
    }
    @GetMapping(value = "/{grupoId}/permissoes/{permissaoId}")
    public ResponseEntity<?> findById(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        return ResponseEntity.ok().body(service.findByIdPermissoes(grupoId, permissaoId));
    }
    @PutMapping(value = "/{grupoId}/permissoes/{permissaoId}")
    public ResponseEntity<Void> associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        service.associarPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{grupoId}/permissoes/{permissaoId}")
    public ResponseEntity<Void> desassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        service.desassociarPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }

}
