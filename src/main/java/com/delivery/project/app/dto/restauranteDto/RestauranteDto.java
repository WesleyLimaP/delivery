package com.delivery.project.app.dto.restauranteDto;

import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.dto.cozinhaDto.CozinhaDto;
import lombok.Data;

import java.util.List;

@Data
public class RestauranteDto {
    private Long id;
    private String nome;
    private Double taxaFrete;
    private CozinhaDto cozinha;
    private List<FormaDePagamentoDto> formasDePagamento;

    public RestauranteDto(Restaurante restaurante){
        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.cozinha = new CozinhaDto(restaurante.getCozinha().getId(), restaurante.getNome());
        this.formasDePagamento = restaurante.getFormasDePagamento().stream().map(x -> new FormaDePagamentoDto(x.getId(), x.getDescricao())).toList();

    }



}
