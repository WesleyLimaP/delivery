package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.fotoProduto.FotoProdutoDto;
import com.delivery.project.app.api.model.dto.filesDto.ImageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Produto Fotos", description = "Gerencia as fotos dos produtos de um restaurante")
public interface RestauranteProdutoFotoControllerDoc {

    @Operation(summary = "Adiciona uma foto a um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Foto adicionada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Arquivo inválido ou dados incorretos"),
        @ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado"),
        @ApiResponse(responseCode = "415", description = "Tipo de mídia não suportado")
    })
    ResponseEntity<FotoProdutoDto> insert(
        @PathVariable Long restauranteId,
        @PathVariable Long produtoId,
        @ModelAttribute ImageDto image
    ) throws IOException;

    @Operation(summary = "Busca a foto de um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Foto retornada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "404", description = "Foto, restaurante ou produto não encontrado")
    })
    ResponseEntity<FotoProdutoDto> get(
        @PathVariable Long restauranteId,
        @PathVariable Long produtoId
    ) throws IOException;

    @Operation(summary = "Busca a imagem de um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagem retornada com sucesso",
                   content = @Content(mediaType = "image/*")),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "404", description = "Imagem, restaurante ou produto não encontrado")
    })
    ResponseEntity<?> getImage(
        @PathVariable Long restauranteId,
        @PathVariable Long produtoId,
        @RequestHeader(name = "Accept") String acceptHeaders
    ) throws HttpMediaTypeNotAcceptableException;

    @Operation(summary = "Remove a foto de um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Foto removida com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "404", description = "Foto, restaurante ou produto não encontrado")
    })
    ResponseEntity<Void> delete(
        @PathVariable Long restauranteId,
        @PathVariable Long produtoId
    );
}
