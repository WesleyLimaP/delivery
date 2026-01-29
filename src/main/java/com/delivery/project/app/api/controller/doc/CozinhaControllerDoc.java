package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cozinhas", description = "Gerencia cozinhas")
public interface CozinhaControllerDoc {

    @Operation(summary = "Lista todas as cozinhas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cozinhas retornada com sucesso")
    })
    CollectionModel<CozinhaDto> findAll();

    @Operation(summary = "Busca uma cozinha por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cozinha encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cozinha não encontrada")
    })
    ResponseEntity<CozinhaDto> findById(@PathVariable Long id);

    @Operation(summary = "Cria uma nova cozinha")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cozinha criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<CozinhaDto> insert(@RequestBody CozinhaDto dto);

    @Operation(summary = "Atualiza uma cozinha existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cozinha atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cozinha não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<CozinhaDto> update(@PathVariable Long id, @RequestBody CozinhaDto dto);

    @Operation(summary = "Remove uma cozinha")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cozinha removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cozinha não encontrada")
    })
    ResponseEntity<?> delete(@PathVariable Long id);
}
