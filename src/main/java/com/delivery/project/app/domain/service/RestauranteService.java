package com.delivery.project.app.domain.service;
import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteAbertoDto;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.domain.model.FormaDePagamento;
import com.delivery.project.app.domain.model.Produto;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteDtoInsert;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDtoSingleSearch;
import com.delivery.project.app.domain.service.util.FindOrFailService;
import com.delivery.project.app.domain.service.util.mapper.produto.ProdutoMapper;
import com.delivery.project.app.domain.service.util.mapper.restaurante.RestauranteMapper;
import com.delivery.project.app.domain.service.util.AssossiationValidatorService;
import com.delivery.project.app.domain.repository.ProdutoRepository;
import com.delivery.project.app.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private ProdutoMapper produtoMapper;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private AssossiationValidatorService assossiationValidatorService;
    @Autowired
    private RestauranteMapper restauranteMapper;
    @Autowired
    private FindOrFailService findOrFailService;


    @Transactional(readOnly = true)
    public List<RestauranteDto> findAll() {
        return restauranteRepository.getAll().stream().map(RestauranteDto::new).toList();
    }


    @Transactional(readOnly = true)
    public RestauranteDtoSingleSearch findById(Long id) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(id);

        return new RestauranteDtoSingleSearch(restaurante);
    }

    @Transactional
    public RestauranteDto insert(RestauranteDtoInsert dto) {
        Cozinha cozinha = findOrFailService.getCozinhaOrElseThrow(dto);
        List<FormaDePagamento> formaDePagamentosObj = findOrFailService.getFormaDePagamentosObj(dto);
        Restaurante restaurante = new Restaurante();
        restauranteMapper.toEntity(restaurante, dto, cozinha, formaDePagamentosObj);
        return new RestauranteDto( restauranteRepository.save(restaurante));
    }



    @Transactional
    public void delete(Long id) {
        try {
            restauranteRepository.deleteById(findOrFailService.getRestauranteOrElseThrow(id).getId());
            restauranteRepository.flush();
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException("Não foi possivel deletar pois o restaurante está em uso");
        }

    }

    @Transactional
    public RestauranteDto update(Long id, RestauranteDtoInsert dto) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(id);

        Cozinha cozinha = findOrFailService.getCozinhaOrElseThrow(dto);

        List<FormaDePagamento> formaDePagamentosObj = findOrFailService.getFormaDePagamentosObj(dto);

        restauranteMapper.toEntity(restaurante, dto, cozinha, formaDePagamentosObj);
        return new RestauranteDto(restaurante);

    }


    @Transactional
    public void desassociarFormaPagamento(Long restId, Long formaPagamanetoId) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(restId);
        FormaDePagamento formaDePagamento = findOrFailService.getFormaPagamentoOrElseThrow(formaPagamanetoId);
        assossiationValidatorService.verificarAssociacaoFormaPagamento(restaurante, formaDePagamento);
        restaurante.getFormasPagamento().remove(formaDePagamento);

    }

    @Transactional
    public void associarFormaPagamento(Long restId, Long formaPagamanetoId) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(restId);
        FormaDePagamento formaDePagamento = findOrFailService.getFormaPagamentoOrElseThrow(formaPagamanetoId);
        FormaDePagamento novaFormaPagamentoObj = findOrFailService.getFormaPagamentoOrElseThrow(formaPagamanetoId);
        restaurante.getFormasPagamento().add(novaFormaPagamentoObj);


    }

    @Transactional(readOnly = true)
    public ProdutoResponseDto findProdutoById(Long restId, Long prodId) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(restId);
        Produto produtoRepo = findOrFailService.getProdutoOrElseThrow(prodId);
        assossiationValidatorService.verificarProdutoAssociado(restaurante, produtoRepo);
        return new ProdutoResponseDto(produtoRepo);
    }



    @Transactional(readOnly = true)
    public List<ProdutoResponseDto> findAllProdutoById(Long restId) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(restId);
        return restaurante.getProdutos().stream().map(ProdutoResponseDto::new).toList();
    }

    @Transactional
    public ProdutoResponseDto insertProduto(Long restId, ProdutoRequestDto produtoDto) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(restId);
        Produto produto = produtoMapper.toEntity(produtoDto, restaurante);
        produto = produtoRepository.save(produto);
        restaurante.getProdutos().add(produto);
        return new ProdutoResponseDto(produto);

    }
    @Transactional
    public ProdutoResponseDto updateProduto(Long restId, Long prodId, ProdutoRequestDto produtoDto) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(restId);
        Produto produto = findOrFailService.getProdutoOrElseThrow(prodId);
        assossiationValidatorService.verificarProdutoAssociado(restaurante, produto);
        produtoMapper.toEntity(produtoDto, produto);
        return new ProdutoResponseDto(produto);

    }


    @Transactional
    public void deleteProduto(Long restId, Long prodId) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(restId);
        Produto produto = findOrFailService.getProdutoOrElseThrow(prodId);
        assossiationValidatorService.verificarProdutoAssociado(restaurante, produto);
        restaurante.getProdutos().remove(produto);
        produtoRepository.delete(produto);
    }
    @Transactional
    public RestauranteAbertoDto abertura(Long id) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(id);
        restaurante.setAberto(true);
        return new RestauranteAbertoDto(restaurante.isAberto());
    }
    @Transactional
    public RestauranteAbertoDto fechamento(Long id) {
        Restaurante restaurante = findOrFailService.getRestauranteOrElseThrow(id);
        restaurante.setAberto(false);
        return new RestauranteAbertoDto(restaurante.isAberto());
    }
}
