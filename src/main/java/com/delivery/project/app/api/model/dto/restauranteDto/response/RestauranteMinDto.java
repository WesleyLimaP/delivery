package com.delivery.project.app.api.model.dto.restauranteDto.response;

import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import com.delivery.project.app.domain.model.Restaurante;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteMinDto extends RepresentationModel<RestauranteMinDto> {
    private Long id;
    private String nome;


}
