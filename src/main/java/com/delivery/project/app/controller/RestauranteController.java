package com.delivery.project.app.controller;

import com.delivery.project.app.domain.service.RestauranteService;
import com.delivery.project.app.dto.restauranteDto.RestauranteDto;
import com.delivery.project.app.dto.restauranteDto.RestauranteDtoInsert;
import com.delivery.project.app.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.exceptions.IdNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/restaurante")
public class RestauranteController {
    @Autowired
    private RestauranteService service;

    @GetMapping
    public ResponseEntity<List<RestauranteDto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestauranteDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RestauranteDto> insert (@RequestBody RestauranteDtoInsert dto){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // pega a URI do POST /restaurantes// acrescenta /{id}
                .buildAndExpand() // substitui {id}
                .toUri();
        return ResponseEntity.created(location).body(service.insert(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        try {
            service.delete(id);
            return status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeEmUsoException e) {
            return status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (IdNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDto> update(@PathVariable Long id, @RequestBody RestauranteDtoInsert dto){
        return ResponseEntity.ok(service.update(id, dto ));
    }

}
