package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.UsuarioGrupoControllerDoc;
import com.delivery.project.app.api.model.dto.usuarioDto.response.GrupoUsuarioResponseDto;
import com.delivery.project.app.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerDoc {
    @Autowired
    private UsuarioService service;

    @GetMapping("/{grupoId}")
    public ResponseEntity<GrupoUsuarioResponseDto> findGrupoById(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        return ResponseEntity.ok().body(service.findGrupoById(usuarioId, grupoId));
    }
    @GetMapping()
    public ResponseEntity<List<GrupoUsuarioResponseDto>> findAllGrupos(@PathVariable Long usuarioId){
        return ResponseEntity.ok().body(service.findAllGrupos(usuarioId));
    }


    @DeleteMapping("/{grupoId}")
    public ResponseEntity<?> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        service.desassociarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{grupoId}")
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        service.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.ok().build();

    }

}
