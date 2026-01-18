package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.EstadoControllerDoc;
import com.delivery.project.app.api.util.LocationBulder;
import com.delivery.project.app.domain.service.EstadoService;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/estados")
public class EstadoController implements EstadoControllerDoc {
    @Autowired
    private EstadoService service;


    @GetMapping
    public ResponseEntity<List<EstadoDto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EstadoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EstadoDto> insert (@RequestBody EstadoDto dto){
        var response = service.insert(dto);
        URI location = LocationBulder.create(response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        try {
            service.delete(id);
            return status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeEmUsoException e) {
            return status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<EstadoDto> update(@PathVariable Long id, @RequestBody EstadoDto dto){
        return ResponseEntity.ok(service.update(id, dto ));
    }
}
