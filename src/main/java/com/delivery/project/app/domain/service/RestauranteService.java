package com.delivery.project.app.domain.service;
import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.domain.model.FormaDePagamento;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.dto.restauranteDto.RestauranteDto;
import com.delivery.project.app.dto.restauranteDto.RestauranteDtoInsert;
import com.delivery.project.app.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.exceptions.IdNaoEncontradoException;
import com.delivery.project.app.repository.CozinhaRepository;
import com.delivery.project.app.repository.FormaDePagamentoRepository;
import com.delivery.project.app.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;

    @Transactional(readOnly = true)
    public List<RestauranteDto> findAll() {
        return restauranteRepository.getAll().stream().map(RestauranteDto::new).toList();
    }


    @Transactional(readOnly = true)
    public RestauranteDto findById(Long id) {
        Restaurante restaurante = restauranteRepository.getId(id).orElseThrow(() ->
                new IdNaoEncontradoException("id nao encontrado"));

        return new RestauranteDto(restaurante);
    }

    @Transactional
    public RestauranteDto insert(RestauranteDtoInsert dto) {
        Cozinha cozinha = cozinhaRepository.findById(dto.getCozinhaId()).orElseThrow(() ->
                new IdNaoEncontradoException("cozinha nao encontrada"));

        List<Optional<FormaDePagamento>> formaDePagamentoList =
                formaDePagamentoRepository.getByIds(dto.getFormasDePagamento());
        
        List<FormaDePagamento> formaDePagamentosObj = formaDePagamentoList.stream().map(
                x -> x.orElseThrow(() -> new IdNaoEncontradoException(
                        "forma de pagamento nao encontrada"
                ))).toList();

        Restaurante restaurante = new Restaurante();
        marge(restaurante, dto, cozinha, formaDePagamentosObj);
        return new RestauranteDto( restauranteRepository.save(restaurante));
    }

    private static void marge(Restaurante restaurante, RestauranteDtoInsert dto, Cozinha cozinha, List<FormaDePagamento> formaDePagamentosObj) {
        restaurante.setCozinha(cozinha);
        restaurante.setNome(dto.getNome());
        restaurante.setTaxaFrete(dto.getTaxaFrete());
        restaurante.setFormasDePagamento(formaDePagamentosObj);
    }

    @Transactional
    public void delete(Long id) {
        try {
            restauranteRepository.deleteById( restauranteRepository.findById(id).orElseThrow(() ->
                    new IdNaoEncontradoException("restaurante nao encontrado")).getId());
            restauranteRepository.flush();
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException("A entidade nao pode ser apagada pois existe dependencia com outras classes" );
        }

    }

    @Transactional
    public RestauranteDto update(Long id, RestauranteDtoInsert dto) {
        Restaurante restaurante = restauranteRepository.getId(id).orElseThrow(() ->
                new IdNaoEncontradoException("restaurante com id " + id + " nao foi encontrado"));

        Cozinha cozinha = cozinhaRepository.findById(dto.getCozinhaId()).orElseThrow(() ->
                new IdNaoEncontradoException("cozinha nao encontrada"));

        List<Optional<FormaDePagamento>> formaDePagamentoList =
                formaDePagamentoRepository.getByIds(dto.getFormasDePagamento());

        List<FormaDePagamento> formaDePagamentosObj = formaDePagamentoList.stream().map(
                x -> x.orElseThrow(() -> new IdNaoEncontradoException(
                        "forma de pagamento nao encontrada"
                ))).toList();

        marge(restaurante, dto, cozinha, formaDePagamentosObj);
        return new RestauranteDto(restaurante);

    }
}
