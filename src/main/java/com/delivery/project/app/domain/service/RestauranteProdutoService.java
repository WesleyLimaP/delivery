package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.assembler.ProdutoAssembler;
import com.delivery.project.app.api.assembler.RestauranteProdutoAssembler;
import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.domain.exceptions.AssociacaoException;
import com.delivery.project.app.domain.exceptions.ProdutoNaoEncontradoException;
import com.delivery.project.app.domain.exceptions.RestauranteNaoEncontradoException;
import com.delivery.project.app.domain.model.Produto;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.domain.repository.ProdutoRepository;
import com.delivery.project.app.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private RestauranteProdutoAssembler assembler;


    @Transactional(readOnly = true)
    public ProdutoResponseDto findProdutoById(Long restId, Long prodId) {
        Restaurante restaurante = getRestauranteOrElseThrow(restId);
        Produto produto = getProdutoOrElseThrow(prodId);
        var assossiacao = restaurante.verificarProdutoAssociado(produto);
        throwAssociacaoException(assossiacao);
        return assembler.toModel(produto);
    }


    @Transactional(readOnly = true)
    public CollectionModel<ProdutoResponseDto> findAllProdutoById(Long restId) {
        Restaurante restaurante = getRestauranteOrElseThrow(restId);
        return assembler.toCollectionModel(restaurante.getProdutos());
    }

    @Transactional
    public ProdutoResponseDto insertProduto(Long restId, ProdutoRequestDto produtoDto) {
       Restaurante restaurante = getRestauranteOrElseThrow(restId);
       Produto produto = assembler.toEntity(produtoDto);
       produto.setRestaurante(restaurante);
        produto = produtoRepository.save(produto);
        restaurante.getProdutos().add(produto);
        return assembler.toModel(produto);

    }
    @Transactional
    public ProdutoResponseDto updateProduto(Long restId, Long prodId, ProdutoRequestDto produtoDto) {
        Restaurante restaurante = getRestauranteOrElseThrow(restId);
        Produto produto = getProdutoOrElseThrow(prodId);
        throwAssociacaoException(restaurante.verificarProdutoAssociado(produto));
        assembler.update(produto, produtoDto);
        return assembler.toModel(produto);

    }


    @Transactional
    public void deleteProduto(Long restId, Long prodId) {
            Restaurante restaurante = getRestauranteOrElseThrow(restId);
            Produto produto = getProdutoOrElseThrow(prodId);
            throwAssociacaoException(restaurante.verificarProdutoAssociado(produto));
            produto.setRestaurante(null);
            restaurante.getProdutos().remove(produto);
            produtoRepository.flush();
    }
    private Produto getProdutoOrElseThrow(Long prodId) {
        return produtoRepository.findById(prodId).orElseThrow(() ->
                new ProdutoNaoEncontradoException("produto nao encontrado"));
    }
    private Restaurante getRestauranteOrElseThrow(Long restId) {
        return restauranteRepository.findById(restId).orElseThrow(() ->
                new RestauranteNaoEncontradoException("restaurante nao encontrado"));
    }
    private void throwAssociacaoException(boolean associacao) {
        if(!associacao){
            throw new AssociacaoException("produto nao associado ao restaurante");
        }
    }
}
