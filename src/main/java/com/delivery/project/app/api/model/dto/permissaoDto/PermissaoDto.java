package com.delivery.project.app.api.model.dto.permissaoDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PermissaoDto extends RepresentationModel<PermissaoDto> {
    private Long id;
    private String descricao;
    @JsonIgnore
    private Long grupoId;
}
