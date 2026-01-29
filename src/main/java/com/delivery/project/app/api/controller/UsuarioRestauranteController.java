package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.UsuarioRestauranteControllerDoc;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/restaurantes")
public class UsuarioRestauranteController implements UsuarioRestauranteControllerDoc {
    @Autowired
    private UsuarioService service;

    @GetMapping("/{restauranteId}")
    public ResponseEntity<RestauranteDto> findRestauranteById(@PathVariable Long usuarioId, @PathVariable Long restauranteId){
        return ResponseEntity.ok().body(service.findRestauranteByid(usuarioId, restauranteId));
    }
    @GetMapping()
    public CollectionModel<RestauranteDto> findAllRestaurante(@PathVariable Long usuarioId){
        return service.findAllRestaurante(usuarioId);
    }


    @DeleteMapping(value = "/{restauranteId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long restauranteId){
        service.desassociarRestaurante(usuarioId, restauranteId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{restauranteId}")
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long restauranteId){
        service.associarRestaurante(usuarioId, restauranteId);
        return ResponseEntity.noContent().build();

    }

}
