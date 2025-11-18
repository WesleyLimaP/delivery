package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurante/{restauranteId}/forma-de-pagamento")
public class RestauranteFormaDePagamentoController {
    @Autowired
    private RestauranteService service;

    @GetMapping()
    public ResponseEntity<List<FormaDePagamentoResponseDto>> findById(@PathVariable Long restauranteId){
        return ResponseEntity.ok().body(service.findById(restauranteId).getFormasDePagamento());
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<?> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        service.desassociarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<List<FormaDePagamentoResponseDto>> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        service.associarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.ok(service.findById(restauranteId).getFormasDePagamento());

    }

}
