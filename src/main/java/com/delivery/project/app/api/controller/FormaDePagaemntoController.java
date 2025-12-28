package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.model.dto.formaDePagamentoDto.request.FormaDePagamentoDescricaoDto;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.domain.service.FormaDePagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping(value = "/formas-de-pagamento")
public class FormaDePagaemntoController {
    @Autowired
    private FormaDePagamentoService service;

    @GetMapping
    public ResponseEntity<List<FormaDePagamentoResponseDto>> getAll(){
        List<FormaDePagamentoResponseDto> formasDePagamento = service.getAll();
        return ResponseEntity.ok(formasDePagamento);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<FormaDePagamentoResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping()
    public ResponseEntity<FormaDePagamentoResponseDto> insert(@RequestBody @Valid FormaDePagamentoDescricaoDto dto){
        FormaDePagamentoResponseDto dtoResponse = service.insert(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // pega a URI do POST /restaurantes// acrescenta /{id}
                .buildAndExpand(dtoResponse.getId()) // substitui {id}
                .toUri();
        return ResponseEntity.created(location).body(dtoResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
       service.delete(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<FormaDePagamentoResponseDto> update(@RequestBody @Valid FormaDePagamentoDescricaoDto dto, @PathVariable Long id){
        FormaDePagamentoResponseDto dtoResponse = service.update(dto, id);
        return ResponseEntity.ok().body(dtoResponse);
    }
}
