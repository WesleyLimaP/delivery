package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.EstadoControllerDoc;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.request.EstadoNomeDto;
import com.delivery.project.app.api.util.LocationBulder;
import com.delivery.project.app.domain.service.EstadoService;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/estados")
public class EstadoController implements EstadoControllerDoc {
    @Autowired
    private EstadoService service;


    @GetMapping
    public CollectionModel<EstadoDto> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EstadoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EstadoDto> insert (@RequestBody @Valid EstadoDto dto){
        var response = service.insert(dto);
        URI location = LocationBulder.create(response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
            service.delete(id);
            return status(HttpStatus.NO_CONTENT).build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<EstadoDto> update(@PathVariable Long id, @RequestBody @Valid EstadoNomeDto dto){
        ResponseEntity.ok().body(service.update(id, dto ));
        return null;
    }
}
