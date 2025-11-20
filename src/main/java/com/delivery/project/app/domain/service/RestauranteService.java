package com.delivery.project.app.domain.service;
import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.api.model.dto.restauranteDto.RestauranteAbertoDto;
import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.domain.model.FormaDePagamento;
import com.delivery.project.app.domain.model.Produto;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.api.model.dto.restauranteDto.RestauranteDto;
import com.delivery.project.app.api.model.dto.restauranteDto.RestauranteDtoInsert;
import com.delivery.project.app.api.model.dto.restauranteDto.RestauranteDtoSingleSearch;
import com.delivery.project.app.exceptions.*;
import com.delivery.project.app.repository.CozinhaRepository;
import com.delivery.project.app.repository.FormaDePagamentoRepository;
import com.delivery.project.app.repository.ProdutoRepository;
import com.delivery.project.app.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {
    public static final String MSG_COZINHA_NAO_ENCONTRADA = "cozinha nao encontrada";
    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante nao encontrado";
    public static final String MSG_INTEGRIDADE_REFERENCIAL = "A entidade nao pode ser apagada pois existe dependencia com outras classes";
    public static final String MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA = "forma de pagamento nao encontrada";
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<RestauranteDto> findAll() {
        return restauranteRepository.getAll().stream().map(RestauranteDto::new).toList();
    }


    @Transactional(readOnly = true)
    public RestauranteDtoSingleSearch findById(Long id) {
        Restaurante restaurante = getOrElseThrow(id);

        return new RestauranteDtoSingleSearch(restaurante);
    }

    @Transactional
    public RestauranteDto insert(RestauranteDtoInsert dto) {
        Cozinha cozinha = getOrElseThrow(dto);
        List<FormaDePagamento> formaDePagamentosObj = getFormaDePagamentosObj(dto);
        Restaurante restaurante = new Restaurante();
        marge(restaurante, dto, cozinha, formaDePagamentosObj);
        return new RestauranteDto( restauranteRepository.save(restaurante));
    }

    private static void marge(Restaurante restaurante, RestauranteDtoInsert dto, Cozinha cozinha, List<FormaDePagamento> formaDePagamentosObj) {
        restaurante.setCozinha(cozinha);
        restaurante.setNome(dto.getNome());
        restaurante.setTaxaFrete(dto.getTaxaFrete());
        restaurante.setAberto(dto.isAberto());
        restaurante.getFormasPagamento().addAll(formaDePagamentosObj);
        restaurante.setEndereco(dto.getEndereco());
    }

    @Transactional
    public void delete(Long id) {
        try {
            restauranteRepository.deleteById(getOrElseThrow(id).getId());
            restauranteRepository.flush();
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(MSG_INTEGRIDADE_REFERENCIAL);
        }

    }

    @Transactional
    public RestauranteDto update(Long id, RestauranteDtoInsert dto) {
        Restaurante restaurante = getOrElseThrow(id);

        Cozinha cozinha = getOrElseThrow(dto);

        List<FormaDePagamento> formaDePagamentosObj = getFormaDePagamentosObj(dto);

        marge(restaurante, dto, cozinha, formaDePagamentosObj);
        return new RestauranteDto(restaurante);

    }

    private Cozinha getOrElseThrow(RestauranteDtoInsert dto) {
        return cozinhaRepository.findById(dto.getCozinhaId()).orElseThrow(() ->
                new RestauranteNaoEncontradoException(MSG_COZINHA_NAO_ENCONTRADA));
    }
    private Restaurante getOrElseThrow(Long id) {
        return restauranteRepository.getId(id).orElseThrow(() ->
                new RestauranteNaoEncontradoException(MSG_RESTAURANTE_NAO_ENCONTRADO));
    }
    private FormaDePagamento getFormaPagamentoOrElseThrow(Long formaPagamentoId) {
        return formaDePagamentoRepository.findById(formaPagamentoId).orElseThrow(() ->
                new FormaDePagamentoEncontradaException(MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA));
    }
    private List<FormaDePagamento> getFormaDePagamentosObj(RestauranteDtoInsert dto) {
        List<Optional<FormaDePagamento>> formaDePagamentoList =
                formaDePagamentoRepository.getByIds(dto.getFormasDePagamento());

        return formaDePagamentoList.stream().map(
                x -> x.orElseThrow(() -> new RestauranteNaoEncontradoException(
                        MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA
                ))).toList();
    }

    @Transactional
    public void desassociarFormaPagamento(Long restId, Long formaPagamanetoId) {
        Restaurante restaurante = getOrElseThrow(restId);
        FormaDePagamento formaDePagamento = getFormaPagamentoOrElseThrow(formaPagamanetoId);
        if(!restaurante.getFormasPagamento().contains(formaDePagamento)){
            throw new AssociacaoException("a forma de pagamento a ser mudada nao pertence a este restaurantw");
        }
        restaurante.getFormasPagamento().remove(formaDePagamento);

    }
    @Transactional
    public void associarFormaPagamento(Long restId, Long formaPagamanetoId) {
        Restaurante restaurante = getOrElseThrow(restId);
        FormaDePagamento formaDePagamento = getFormaPagamentoOrElseThrow(formaPagamanetoId);
        FormaDePagamento novaFormaPagamentoObj = getFormaPagamentoOrElseThrow(formaPagamanetoId);
        restaurante.getFormasPagamento().add(novaFormaPagamentoObj);


    }

    @Transactional(readOnly = true)
    public ProdutoResponseDto findProdutoById(Long restId, Long prodId) {
        Restaurante restaurante = getOrElseThrow(restId);
        Produto produtoRepo = getProdutoOrElseThrow(prodId);
        verificarProdutoAssociado(restaurante, produtoRepo);
        return new ProdutoResponseDto(produtoRepo);
    }

    private Produto getProdutoOrElseThrow(Long prodId) {
        return produtoRepository.findById(prodId).orElseThrow(() ->
                new ProdutoNaoEncontradoException("produto nao encontrado"));
    }

    private void verificarProdutoAssociado(Restaurante restaurante, Produto produtoRepo) {
        if(!restaurante.getProdutos().contains(produtoRepo)) {
            new AssociacaoException("produto informado nao pertence a este restaurante");
        }
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDto> findAllProdutoById(Long restId) {
        Restaurante restaurante = getOrElseThrow(restId);
        return restaurante.getProdutos().stream().map(ProdutoResponseDto::new).toList();
    }

    @Transactional
    public ProdutoResponseDto insertProduto(Long restId, ProdutoRequestDto produtoDto) {
        Restaurante restaurante = getOrElseThrow(restId);
        Produto produto = mergeDtoToProduto(produtoDto, restaurante);
        produto = produtoRepository.save(produto);
        restaurante.getProdutos().add(produto);
        return new ProdutoResponseDto(produto);

    }
    @Transactional
    public ProdutoResponseDto updateProduto(Long restId, Long prodId, ProdutoRequestDto produtoDto) {
        Restaurante restaurante = getOrElseThrow(restId);
        Produto produto = getProdutoOrElseThrow(prodId);
        mergeDtoToProduto(produtoDto, produto);
        return new ProdutoResponseDto(produto);

    }

    //merge para insert
    private Produto mergeDtoToProduto(ProdutoRequestDto dto, Restaurante restaurante){
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setAtivo(dto.isAtivo());
        produto.setPreco(dto.getPreco());
        produto.setRestaurante(restaurante);
        return produto;
    }
    //merge para update
    private void mergeDtoToProduto(ProdutoRequestDto dto, Produto produto){
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setAtivo(dto.isAtivo());
        produto.setPreco(dto.getPreco());
    }

    @Transactional
    public void deleteProduto(Long restId, Long prodId) {
        Restaurante restaurante = getOrElseThrow(restId);
        Produto produto = getProdutoOrElseThrow(prodId);
        verificarProdutoAssociado(restaurante, produto);
        restaurante.getProdutos().remove(produto);
        produtoRepository.delete(produto);
    }
    @Transactional
    public RestauranteAbertoDto abertura(Long id) {
        Restaurante restaurante = getOrElseThrow(id);
        restaurante.setAberto(true);
        return new RestauranteAbertoDto(restaurante.isAberto());
    }
    @Transactional
    public RestauranteAbertoDto fechamento(Long id) {
        Restaurante restaurante = getOrElseThrow(id);
        restaurante.setAberto(false);
        return new RestauranteAbertoDto(restaurante.isAberto());
    }
}
