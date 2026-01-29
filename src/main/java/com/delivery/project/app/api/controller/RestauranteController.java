package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.RestauranteControllerDoc;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteAbertoDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteInsertDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteUpdateDto;
import com.delivery.project.app.api.util.LocationBulder;
import com.delivery.project.app.domain.service.RestauranteService;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDtoSingleSearch;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/restaurante")
public class RestauranteController implements RestauranteControllerDoc {
    @Autowired
    private RestauranteService service;

    @GetMapping
    public ResponseEntity<CollectionModel<RestauranteDto>> findAll(){
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestauranteDtoSingleSearch> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RestauranteDto> insert (@RequestBody @Valid RestauranteInsertDto dto){
        URI location = LocationBulder.create(service.insert(dto).getId());
        return ResponseEntity.created(location).body(service.insert(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
            service.delete(id);
            return status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDto> update(@PathVariable Long id, @RequestBody @Valid RestauranteUpdateDto dto){
        return ResponseEntity.ok(service.update(id, dto ));
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
