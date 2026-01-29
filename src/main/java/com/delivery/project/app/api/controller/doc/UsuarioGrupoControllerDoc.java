package com.delivery.project.app.api.controller.doc;


import com.delivery.project.app.api.model.dto.usuarioDto.response.GrupoUsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuário Grupos", description = "Gerencia os grupos de um usuário")
public interface UsuarioGrupoControllerDoc {

    @Operation(summary = "Lista todos os grupos de um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de grupos retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    CollectionModel<GrupoUsuarioResponseDto> findAllGrupos(@PathVariable Long usuarioId);

    @Operation(summary = "Associa um usuário a um grupo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário associado ao grupo com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado")
    })
    ResponseEntity<Void> associar(
        @PathVariable Long usuarioId,
        @PathVariable Long grupoId
    );

    @Operation(summary = "Remove a associação de um usuário de um grupo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário removido do grupo com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado")
    })
    ResponseEntity<?> desassociar(
        @PathVariable Long usuarioId,
        @PathVariable Long grupoId
    );

    @Operation(summary = "Busca um grupo específico de um usuário",
            description = "Retorna os detalhes de um grupo específico associado a um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grupo encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
     ResponseEntity<com.delivery.project.app.api.model.dto.usuarioDto.response.GrupoUsuarioResponseDto> findGrupoById(@PathVariable Long usuarioId, @PathVariable Long grupoId);
}
