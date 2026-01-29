package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.PedidoControllerDoc;
import com.delivery.project.app.api.model.dto.pedidoDto.request.PedidoRequestDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoMaxDto;
import com.delivery.project.app.api.model.filter.PedidoFilter;
import com.delivery.project.app.api.util.LocationBulder;
import com.delivery.project.app.domain.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/pedidos")
public class PedidoController implements PedidoControllerDoc {
    @Autowired
    private PedidoService service;
    @Autowired
    private PagedResourcesAssembler<PedidoDto > pagedResourcesAssembler;


    @GetMapping
    public PagedModel<PedidoDto> findAll(PedidoFilter filter, Pageable pageable){
        return service.findAll( filter, pageable);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoMaxDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }
    @PutMapping(value = "/{id}/confirmacao")
    public ResponseEntity<Void> confirmarPedido(@PathVariable Long id){
        service.UpdateStatusToConfirmado(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}/entregue")
    public ResponseEntity<Void> entregarPedido(@PathVariable Long id){
        service.UpdateStatusToEntregue(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}/cancelamento")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id){
        service.UpdateStatusToCancelado(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<PedidoMaxDto> insert(@RequestBody @Valid PedidoRequestDto dto){
        var response = service.insert(dto);
        URI location = LocationBulder.create(response.getId());

        return ResponseEntity.created(location).body(response);
    }


}
