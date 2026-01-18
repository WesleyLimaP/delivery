package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.CozinhaControllerDoc;
import com.delivery.project.app.api.util.LocationBulder;
import com.delivery.project.app.domain.service.CozinhaService;
import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.exceptions.EntidadeNaoEncontradaException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController implements CozinhaControllerDoc {
    @Autowired
    private CozinhaService service;


    @GetMapping
    public ResponseEntity<List<CozinhaDto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CozinhaDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CozinhaDto> insert (@RequestBody @Valid CozinhaDto dto){
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
    public ResponseEntity<CozinhaDto> update(@PathVariable Long id, @RequestBody @Valid CozinhaDto dto){
        return ResponseEntity.ok(service.update(id, dto ));
    }
}
