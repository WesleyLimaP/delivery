package com.delivery.project.app.api.model.dto.grupoDto.response;

import com.delivery.project.app.domain.model.Grupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "grupos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GrupoResponseDto extends RepresentationModel<GrupoResponseDto> {
    private Long id;
    private String nome;


}
