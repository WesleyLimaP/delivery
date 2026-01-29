package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.permissaoDto.PermissaoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Grupo Permissões", description = "Gerencia as permissões de um grupo")
public interface GrupoPermissaoControllerDoc {

    @Operation(summary = "Lista todas as permissões de um grupo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de permissões retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
    })
    CollectionModel<PermissaoDto> findAllPermissoes(@PathVariable Long grupoId);

    @Operation(summary = "Associa uma permissão a um grupo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Permissão associada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada")
    })
    ResponseEntity<Void> associarPermissao(
        @PathVariable Long grupoId,
        @PathVariable Long permissaoId
    );

    @Operation(summary = "Remove a associação de uma permissão de um grupo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Permissão desassociada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada")
    })
    ResponseEntity<Void> desassociarPermissao(
        @PathVariable Long grupoId,
        @PathVariable Long permissaoId
    );
}
