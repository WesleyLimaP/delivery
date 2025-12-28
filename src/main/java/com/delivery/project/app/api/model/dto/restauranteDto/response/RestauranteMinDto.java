package com.delivery.project.app.api.model.dto.restauranteDto.response;

import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import com.delivery.project.app.domain.model.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteMinDto {
    private Long id;
    private String nome;

    public RestauranteMinDto(Restaurante restaurante){
        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
    }



}
