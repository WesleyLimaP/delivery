package com.delivery.project.app.api.model.dto.permissaoDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PermissaoDto extends RepresentationModel<PermissaoDto> {
    private Long id;
    private String descricao;
}
