package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.model.dto.pedidoDto.request.PedidoRequestDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoMaxDto;
import com.delivery.project.app.api.model.filter.PedidoFilter;
import com.delivery.project.app.domain.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;


    @GetMapping
    public ResponseEntity<Page<List<PedidoDto>>> findAll(PedidoFilter filter, Pageable pageable){
        return ResponseEntity.ok().body(service.findAll( filter, pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoMaxDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }
    @PutMapping(value = "/{id}/confirmado")
    public ResponseEntity<PedidoMaxDto> updateStatusToConfirmado(@PathVariable Long id){
        return ResponseEntity.ok().body(service.UpdateStatusToConfirmado(id));
    }
    @PutMapping(value = "/{id}/entregue")
    public ResponseEntity<PedidoMaxDto> updateStatusToEntregue(@PathVariable Long id){
        return ResponseEntity.ok().body(service.UpdateStatusToEntregue(id));
    }
    @PutMapping(value = "/{id}/cancelado")
    public ResponseEntity<PedidoMaxDto> updateStatusToCancelado(@PathVariable Long id){
        return ResponseEntity.ok().body(service.UpdateStatusToCancelado(id));
    }
    @PostMapping()
    public ResponseEntity<PedidoMaxDto> insert(@RequestBody @Valid PedidoRequestDto dto){
        return ResponseEntity.ok().body(service.insert(dto));
    }


}
