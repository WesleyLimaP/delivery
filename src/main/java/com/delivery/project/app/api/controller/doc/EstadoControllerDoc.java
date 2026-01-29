package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.request.EstadoNomeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Estados", description = "Gerencia estados")
public interface EstadoControllerDoc {

    @Operation(summary = "Lista todos os estados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de estados retornada com sucesso")
    })
    CollectionModel<EstadoDto> findAll();

    @Operation(summary = "Busca um estado por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estado não encontrado")
    })
    ResponseEntity<EstadoDto> findById(@PathVariable Long id);

    @Operation(summary = "Cria um novo estado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Estado criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<EstadoDto> insert(@RequestBody EstadoDto dto);

    @Operation(summary = "Atualiza um estado existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estado não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<EstadoDto> update(@PathVariable Long id, @RequestBody EstadoNomeDto dto);

    @Operation(summary = "Remove um estado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Estado removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estado não encontrado")
    })
    ResponseEntity<?> delete(@PathVariable Long id);
}
