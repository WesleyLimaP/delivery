package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.api.model.dto.grupoDto.response.GrupoResponseDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.GrupoUsuarioResponseDto;
import com.delivery.project.app.domain.model.Grupo;
import com.delivery.project.app.domain.service.RestauranteService;
import com.delivery.project.app.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
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
    public ResponseEntity<GrupoUsuarioResponseDto> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        service.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.ok(service.associarGrupo(usuarioId, grupoId));

    }

}
