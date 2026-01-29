package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.RestauranteProdutoDoc;
import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.domain.service.RestauranteProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/restaurantes/{restId}/produtos")
public class RestauranteProdutoController implements RestauranteProdutoDoc {
    @Autowired
    private RestauranteProdutoService service;




    @GetMapping(value = "/{prodId}")
    public ResponseEntity<ProdutoResponseDto> findProdutoById(@PathVariable Long restId, @PathVariable Long prodId){
        return ResponseEntity.ok().body(service.findProdutoById(restId, prodId));
    }
    @GetMapping()
    public CollectionModel<ProdutoResponseDto> findAllProduto(@PathVariable Long restId){
        return service.findAllProdutoById(restId);
    }
    @PostMapping()
    public ResponseEntity<ProdutoResponseDto> insertProduto(@PathVariable Long restId, @Valid @RequestBody ProdutoRequestDto dto){
        ProdutoResponseDto dtoResult = service.insertProduto(restId, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(dtoResult.getId())
                .toUri();

        return ResponseEntity.created(location).body(dtoResult);
    }
    @PutMapping(value = "/{prodId}")
    public ResponseEntity<ProdutoResponseDto> updateProduto(@PathVariable Long restId, @PathVariable Long prodId, @Valid @RequestBody ProdutoRequestDto dto){
        return ResponseEntity.ok().body(service.updateProduto(restId, prodId, dto));
    }
    @DeleteMapping(value = "/{prodId}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long restId, @PathVariable Long prodId){
        service.deleteProduto(restId, prodId);
        return ResponseEntity.noContent().build();
    }

}
