package com.delivery.project.app.api.model.dto.restauranteDto.request;

import com.delivery.project.app.api.model.dto.endereco.request.EnderecoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteUpdateDto extends RepresentationModel<RestauranteUpdateDto> {
    private String nome;
    private Double taxaFrete;
    private Long cozinhaId;
    private EnderecoRequestDto endereco;
}
