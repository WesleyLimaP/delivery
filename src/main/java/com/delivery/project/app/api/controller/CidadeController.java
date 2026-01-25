package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.CidadeControllerDoc;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeRequestDto;
import com.delivery.project.app.api.util.LocationBulder;
import com.delivery.project.app.domain.service.CidadeService;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.response.CidadeDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController implements CidadeControllerDoc {
    @Autowired
    private CidadeService service;


    @GetMapping
    public ResponseEntity<List<CidadeDto>> findAll(){
        var response = service.findAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CidadeDto> findById(@PathVariable Long id){
        CidadeDto response = service.findById(id);
       return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<CidadeDto> insert (@RequestBody @Valid CidadeRequestDto dto){
        var response = service.insert(dto);
        var location = LocationBulder.create(response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
         service.delete(id);
         return ResponseEntity.noContent().build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<CidadeDto> update(@PathVariable Long id, @RequestBody CidadeUpdateDto dto){
        return ResponseEntity.ok().body(service.update(id, dto));
    }
}
