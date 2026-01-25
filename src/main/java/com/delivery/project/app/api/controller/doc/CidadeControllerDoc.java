package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeRequestDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeUpdateDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.response.CidadeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cidades", description = "Gerencia cidades")
public interface
CidadeControllerDoc {

    @Operation(summary = "Lista todas as cidades")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cidades retornada com sucesso")
    })
    ResponseEntity<List<CidadeDto>> findAll();

    @Operation(summary = "Busca uma cidade por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cidade encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cidade não encontrada")
    })
    ResponseEntity<CidadeDto> findById(@PathVariable Long id);

    @Operation(summary = "Cria uma nova cidade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cidade criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<CidadeDto> insert(@RequestBody CidadeRequestDto dto);

    @Operation(summary = "Atualiza uma cidade existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cidade atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cidade não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<CidadeDto> update(@PathVariable Long id, @RequestBody CidadeUpdateDto dto);

    @Operation(summary = "Remove uma cidade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cidade removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cidade não encontrada")
    })
    ResponseEntity<?> delete(@PathVariable Long id);
}
