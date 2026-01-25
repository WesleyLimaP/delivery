package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produtos", description = "Operações relacionadas a produtos")
public interface RestauranteProdutoDoc {

    @Operation(summary = "Busca um produto específico de um restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado")
    })
    ResponseEntity<ProdutoResponseDto> findProdutoById(
            @PathVariable Long restId,
            @PathVariable Long prodId
    );

    @Operation(summary = "Lista todos os produtos de um restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    ResponseEntity<List<ProdutoResponseDto>> findAllProduto(@PathVariable Long restId);

    @Operation(summary = "Adiciona um novo produto ao restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
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
    ResponseEntity<Void> deleteProduto(
            @PathVariable Long restId,
            @PathVariable Long prodId
    );
}
