package com.delivery.project.app.api.model.dto.restauranteDto;

import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestauranteDtoSingleSearch {
    private Long id;
    private String nome;
    private Double taxaFrete;
    private CozinhaDto cozinha;
    private List<FormaDePagamentoResponseDto> formasDePagamento = new ArrayList<>();
    private EnderecoDto endereco;

    public RestauranteDtoSingleSearch(Restaurante restaurante){
        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.cozinha = new CozinhaDto(restaurante.getCozinha().getId(), restaurante.getNome());
        this.formasDePagamento = restaurante.getFormasPagamento().stream().map(FormaDePagamentoResponseDto::new).toList();
        this.endereco = new EnderecoDto(restaurante.getEndereco());
    }



}
