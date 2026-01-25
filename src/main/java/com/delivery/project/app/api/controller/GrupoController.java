package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.GrupoControllerDoc;
import com.delivery.project.app.api.model.dto.grupoDto.response.GrupoResponseDto;
import com.delivery.project.app.api.model.dto.grupoDto.request.GrupoRequestDto;
import com.delivery.project.app.api.util.LocationBulder;
import com.delivery.project.app.domain.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoController implements GrupoControllerDoc {
        @Autowired
        private GrupoService service;

        @GetMapping
        public ResponseEntity<List<GrupoResponseDto>> getAll(){
            List<GrupoResponseDto> grupos = service.getAll();
            return ResponseEntity.ok(grupos);
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity<GrupoResponseDto> getById(@PathVariable Long id){
            return ResponseEntity.ok(service.getById(id));
        }

        @PostMapping()
        public ResponseEntity<GrupoResponseDto> insert(@RequestBody @Valid GrupoRequestDto dto){
            GrupoResponseDto dtoResponse = service.insert(dto);
            URI location = LocationBulder.create(dtoResponse);
            return ResponseEntity.created(location).body(dtoResponse);
        }



    @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id){
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        @PutMapping("/{id}")
        public ResponseEntity<GrupoResponseDto> update(@RequestBody @Valid GrupoRequestDto dto, @PathVariable Long id){
            GrupoResponseDto dtoResponse = service.update(dto, id);
            return ResponseEntity.ok().body(dtoResponse);
        }



}