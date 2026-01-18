package com.delivery.project.app.api.controller.doc;

import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioPostRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateSenhaDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMaxResponse;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMinResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuários", description = "Gerencia usuários do sistema")
public interface UsuarioControllerDoc {

    @Operation(summary = "Lista todos os usuários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    })
    ResponseEntity<List<UsuarioMinResponse>> findAll();

    @Operation(summary = "Busca um usuário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<UsuarioMaxResponse> findById(@PathVariable Long id);

    @Operation(summary = "Cria um novo usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "409", description = "E-mail já em uso")
    })
    ResponseEntity<UsuarioMaxResponse> insert(@RequestBody UsuarioPostRequestDto usuarioDto);

    @Operation(summary = "Atualiza um usuário existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<UsuarioMaxResponse> update(@PathVariable Long id, @RequestBody UsuarioUpdateRequestDto usuarioDto);

    @Operation(summary = "Atualiza a senha de um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Senha atual inválida"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<UsuarioMaxResponse> updateSenha(
        @PathVariable Long id,
        @RequestBody UsuarioUpdateSenhaDto usuarioSenhaDto
    );

    @Operation(summary = "Remove um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<Void> delete(@PathVariable Long id);
}
