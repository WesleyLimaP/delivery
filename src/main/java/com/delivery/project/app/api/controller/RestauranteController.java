package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteAbertoDto;
import com.delivery.project.app.domain.service.RestauranteService;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteDtoInsert;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDtoSingleSearch;
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
@RequestMapping(value = "/restaurante")
public class RestauranteController {
    @Autowired
    private RestauranteService service;

    @GetMapping
    public ResponseEntity<List<RestauranteDto>> findAll(){
        return ResponseEntity
                .ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestauranteDtoSingleSearch> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RestauranteDto> insert (@RequestBody @Valid RestauranteDtoInsert dto){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
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
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDto> update(@PathVariable Long id, @RequestBody @Valid RestauranteDtoInsert dto){
        return ResponseEntity.ok(service.update(id, dto ));
    }

    //SUB RECURSOS DE RESTAURANTE

    @GetMapping(value = "/{restId}/produtos/{prodId}")
    public ResponseEntity<ProdutoResponseDto> findProdutoById(@PathVariable Long restId, @PathVariable Long prodId){
        return ResponseEntity.ok().body(service.findProdutoById(restId, prodId));
    }
    @GetMapping(value = "/{restId}/produtos")
    public ResponseEntity<List<ProdutoResponseDto>> findAllProduto(@PathVariable Long restId){
        return ResponseEntity.ok().body(service.findAllProdutoById(restId));
    }
    @PostMapping(value = "/{restId}/produtos")
    public ResponseEntity<ProdutoResponseDto> insertProduto(@PathVariable Long restId, @Valid @RequestBody ProdutoRequestDto dto){
        ProdutoResponseDto dtoResult = service.insertProduto(restId, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(dtoResult.getId())
                .toUri();

        return ResponseEntity.created(location).body(dtoResult);
    }
    @PutMapping(value = "/{restId}/produtos/{prodId}")
    public ResponseEntity<ProdutoResponseDto> updateProduto(@PathVariable Long restId, @PathVariable Long prodId, @Valid @RequestBody ProdutoRequestDto dto){
        return ResponseEntity.ok().body(service.updateProduto(restId, prodId, dto));
    }
    @DeleteMapping(value = "/{restId}/produtos/{prodId}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long restId, @PathVariable Long prodId){
        service.deleteProduto(restId, prodId);
        return ResponseEntity.noContent().build();
    }


    //RECURSOS DE ABERTURA E FECHAMENTO DE RESTAURANTE

    @PutMapping(value = "/{id}/abertura")
    public ResponseEntity<RestauranteAbertoDto> abrirRestaurante(@PathVariable Long id){
        return ResponseEntity.ok().body(service.abertura(id));
    }
    @PutMapping(value = "/{id}/fechamento")
    public ResponseEntity<RestauranteAbertoDto> fecharRestaurante(@PathVariable Long id){
        return ResponseEntity.ok().body(service.fechamento(id));
    }

}
