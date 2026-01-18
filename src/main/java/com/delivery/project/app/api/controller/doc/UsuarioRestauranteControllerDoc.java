package com.delivery.project.app.api.controller.doc;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuário Restaurantes", description = "Gerencia os restaurantes de um usuário")
public interface UsuarioRestauranteControllerDoc {

    @Operation(summary = "Lista todos os restaurantes de um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<List<com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto>> findAllRestaurante(@PathVariable Long usuarioId);

    @Operation(summary = "Associa um usuário a um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário associado ao restaurante com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário ou restaurante não encontrado")
    })
    ResponseEntity<Void> associar(
        @PathVariable Long usuarioId,
        @PathVariable Long restauranteId
    );

    @Operation(summary = "Remove a associação de um usuário de um restaurante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário removido do restaurante com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário ou restaurante não encontrado")
    })
    ResponseEntity<Void> desassociar(
        @PathVariable Long usuarioId,
        @PathVariable Long restauranteId
    );
}
