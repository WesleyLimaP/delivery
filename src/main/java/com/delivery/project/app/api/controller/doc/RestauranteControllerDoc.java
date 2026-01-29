package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteAbertoDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteInsertDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteUpdateDto;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDtoSingleSearch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Restaurantes", description = "Gerencia restaurantes e seus produtos")
public interface RestauranteControllerDoc {

    @Operation(summary = "Lista todos os restaurantes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso")
    })
    @GetMapping
    ResponseEntity<CollectionModel<RestauranteDto>> findAll();

    @Operation(summary = "Busca um restaurante por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restaurante encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @GetMapping("/{id}")
    ResponseEntity<RestauranteDtoSingleSearch> findById(@PathVariable Long id);

    @Operation(summary = "Cria um novo restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    ResponseEntity<RestauranteDto> insert(@RequestBody RestauranteInsertDto dto);

    @Operation(summary = "Atualiza um restaurante existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restaurante atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PutMapping("/{id}")
    ResponseEntity<RestauranteDto> update(
        @PathVariable Long id, 
        @RequestBody RestauranteUpdateDto dto
    );

    @Operation(summary = "Remove um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Restaurante removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado"),
        @ApiResponse(responseCode = "409", description = "Restaurante em uso")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id);


    @Operation(summary = "Abre um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restaurante aberto com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @PutMapping("/{id}/abertura")
    ResponseEntity<RestauranteAbertoDto> abrirRestaurante(@PathVariable Long id);

    @Operation(summary = "Fecha um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restaurante fechado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @PutMapping("/{id}/fechamento")
    ResponseEntity<RestauranteAbertoDto> fecharRestaurante(@PathVariable Long id);
}