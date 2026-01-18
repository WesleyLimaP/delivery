package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteAbertoDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteDtoInsert;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDtoSingleSearch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(name = "Restaurantes", description = "Gerencia restaurantes e seus produtos")
public interface RestauranteControllerDoc {

    @Operation(summary = "Lista todos os restaurantes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso")
    })
    @GetMapping
    ResponseEntity<List<RestauranteDto>> findAll();

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
    ResponseEntity<RestauranteDto> insert(@RequestBody RestauranteDtoInsert dto);

    @Operation(summary = "Atualiza um restaurante existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restaurante atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PutMapping("/{id}")
    ResponseEntity<RestauranteDto> update(
        @PathVariable Long id, 
        @RequestBody RestauranteDtoInsert dto
    );

    @Operation(summary = "Remove um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Restaurante removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado"),
        @ApiResponse(responseCode = "409", description = "Restaurante em uso")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id);

    @Operation(summary = "Busca um produto específico de um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado")
    })
    @GetMapping("/{restId}/produtos/{prodId}")
    ResponseEntity<ProdutoResponseDto> findProdutoById(
        @PathVariable Long restId, 
        @PathVariable Long prodId
    );

    @Operation(summary = "Lista todos os produtos de um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @GetMapping("/{restId}/produtos")
    ResponseEntity<List<ProdutoResponseDto>> findAllProduto(@PathVariable Long restId);

    @Operation(summary = "Adiciona um novo produto ao restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto adicionado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @PostMapping("/{restId}/produtos")
    ResponseEntity<ProdutoResponseDto> insertProduto(
        @PathVariable Long restId, 
        @RequestBody ProdutoRequestDto dto
    );

    @Operation(summary = "Atualiza um produto do restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado")
    })
    @PutMapping("/{restId}/produtos/{prodId}")
    ResponseEntity<ProdutoResponseDto> updateProduto(
        @PathVariable Long restId, 
        @PathVariable Long prodId, 
        @RequestBody ProdutoRequestDto dto
    );

    @Operation(summary = "Remove um produto do restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado")
    })
    @DeleteMapping("/{restId}/produtos/{prodId}")
    ResponseEntity<Void> deleteProduto(
        @PathVariable Long restId, 
        @PathVariable Long prodId
    );

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