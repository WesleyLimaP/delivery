package com.delivery.project.app.domain.service.util;

import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteDtoInsert;
import com.delivery.project.app.domain.model.*;
import com.delivery.project.app.domain.repository.*;
import com.delivery.project.app.domain.exceptions.FormaDePagamentoEncontradaException;
import com.delivery.project.app.domain.exceptions.PedidoNaoEncontradoException;
import com.delivery.project.app.domain.exceptions.ProdutoNaoEncontradoException;
import com.delivery.project.app.domain.exceptions.RestauranteNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindOrFailService {
    public static final String MSG_COZINHA_NAO_ENCONTRADA = "cozinha nao encontrada";
    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante nao encontrado";
    public static final String MSG_INTEGRIDADE_REFERENCIAL = "A entidade nao pode ser apagada pois existe dependencia com outras classes";
    public static final String MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA = "forma de pagamento nao encontrada";

    @Autowired
    CozinhaRepository cozinhaRepository;
    @Autowired
    RestauranteRepository restauranteRepository;
    @Autowired
    FormaDePagamentoRepository formaDePagamentoRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    public Cozinha getCozinhaOrElseThrow(RestauranteDtoInsert dto) {
        return cozinhaRepository.findById(dto.getCozinhaId()).orElseThrow(() ->
                new RestauranteNaoEncontradoException(MSG_COZINHA_NAO_ENCONTRADA));
    }
    public Restaurante getRestauranteOrElseThrow(Long id) {
        return restauranteRepository.getId(id).orElseThrow(() ->
                new RestauranteNaoEncontradoException(MSG_RESTAURANTE_NAO_ENCONTRADO));
    }
    public FormaDePagamento getFormaPagamentoOrElseThrow(Long formaPagamentoId) {
        return formaDePagamentoRepository.findById(formaPagamentoId).orElseThrow(() ->
                new FormaDePagamentoEncontradaException(MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA));
    }
    public List<FormaDePagamento> getFormaDePagamentosObj(RestauranteDtoInsert dto) {
        List<Optional<FormaDePagamento>> formaDePagamentoList =
                formaDePagamentoRepository.getByIds(dto.getFormasDePagamento());

        return formaDePagamentoList.stream().map(
                x -> x.orElseThrow(() -> new RestauranteNaoEncontradoException(
                        MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA
                ))).toList();
    }
    public Produto getProdutoOrElseThrow(Long prodId) {
        return produtoRepository.findById(prodId).orElseThrow(() ->
                new ProdutoNaoEncontradoException("produto nao encontrado"));
    }
    public Pedido getPedidoOrElseThrow(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException("pedido nao encontrado"));
    }
}
