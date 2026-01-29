package com.delivery.project.app.api.model.dto.usuarioDto.response;

import com.delivery.project.app.domain.model.Grupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

//essa classe nao tem nada haver com a classe intermediaria entre grupo e usuario.
// ela so modela um grupo para setar o relacionamento com usuario

@Relation(collectionRelation = "grupos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoUsuarioResponseDto extends RepresentationModel<GrupoUsuarioResponseDto> {
    private Long id;
    private String nome;

}
