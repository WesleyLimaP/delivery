package com.delivery.project.app.api.controller.doc;


import com.delivery.project.app.api.model.dto.grupoDto.request.GrupoRequestDto;
import com.delivery.project.app.api.model.dto.grupoDto.response.GrupoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grupos", description = "Gerencia grupos de usuários")
public interface GrupoControllerDoc {

    @Operation(summary = "Lista todos os grupos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de grupos retornada com sucesso")
    })
    ResponseEntity<List<GrupoResponseDto>> getAll();

    @Operation(summary = "Busca um grupo por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Grupo encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
    })
    ResponseEntity<GrupoResponseDto> getById(@PathVariable Long id);

    @Operation(summary = "Cria um novo grupo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Grupo criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<GrupoResponseDto> insert(@RequestBody GrupoRequestDto grupoDto);

    @Operation(summary = "Atualiza um grupo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Grupo atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<GrupoResponseDto> update( @RequestBody GrupoRequestDto grupoDto, @PathVariable Long id);

    @Operation(summary = "Remove um grupo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Grupo removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
    })
    ResponseEntity<Void> delete(@PathVariable Long id);
}
