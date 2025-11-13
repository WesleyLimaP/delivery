package com.delivery.project.app.api.controller;


import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioPostRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateSenhaDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMaxResponse;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMinResponse;
import com.delivery.project.app.domain.model.Usuario;
import com.delivery.project.app.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;


    @GetMapping
    public ResponseEntity<List<UsuarioMinResponse>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioMaxResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioMaxResponse> insert (@RequestBody UsuarioPostRequestDto dto){
        UsuarioMaxResponse dtoResponse = service.insert(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // pega a URI do POST /restaurantes// acrescenta /{id}
                .buildAndExpand() // substitui {id}
                .toUri();
        return ResponseEntity.created(location).body(dtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
            service.delete(id);
            return ResponseEntity.noContent().build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioMaxResponse> update(@PathVariable Long id, @RequestBody UsuarioUpdateRequestDto dto){
        return ResponseEntity.ok(service.update(id, dto ));
    }
    @PutMapping("/{id}/senha")
    public ResponseEntity<UsuarioMaxResponse> update(@PathVariable Long id, @RequestBody UsuarioUpdateSenhaDto dto){
        return ResponseEntity.ok(service.update(id, dto ));
    }
}
