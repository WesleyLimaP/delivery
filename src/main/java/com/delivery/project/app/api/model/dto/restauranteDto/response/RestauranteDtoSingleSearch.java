package com.delivery.project.app.api.model.dto.restauranteDto.response;

import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.api.model.dto.endereco.response.EnderecoDto;
import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RestauranteDtoSingleSearch extends RepresentationModel<RestauranteDtoSingleSearch> {
    private Long id;
    private String nome;
    private boolean aberto;
    private Double taxaFrete;
    private CozinhaDto cozinha;
    private List<FormaDePagamentoResponseDto> formasPagamento = new ArrayList<>();
    private EnderecoDto endereco;



}
