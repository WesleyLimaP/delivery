package com.delivery.project.app.api.model.dto.restauranteDto;

import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDto {
    private Long id;
    private String nome;
    private boolean aberto;
    private Double taxaFrete;
    private CozinhaDto cozinha;

    public RestauranteDto(Restaurante restaurante){
        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
        this.aberto = restaurante.isAberto();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.cozinha = new CozinhaDto(restaurante.getCozinha().getId(), restaurante.getNome());

    }



}
