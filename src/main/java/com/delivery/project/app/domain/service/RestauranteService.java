package com.delivery.project.app.domain.service;
import com.delivery.project.app.api.assembler.RestauranteAssembler;
import com.delivery.project.app.api.assembler.RestauranteDtoSingleSearchAssembler;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteAbertoDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteInsertDto;
import com.delivery.project.app.api.model.dto.restauranteDto.request.RestauranteUpdateDto;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.exceptions.FormaDePagamentoEncontradaException;
import com.delivery.project.app.domain.exceptions.RestauranteNaoEncontradoException;
import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.domain.model.FormaDePagamento;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDto;
import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteDtoSingleSearch;
import com.delivery.project.app.domain.repository.CozinhaRepository;
import com.delivery.project.app.domain.repository.FormaDePagamentoRepository;
import com.delivery.project.app.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {
    public static final String MSG_COZINHA_NAO_ENCONTRADA = "cozinha nao encontrada";
    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante nao encontrado";
    private static final String MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA = "forma de pagamento nao encontrada";
    private static final String MSG_INTEGRIDADE_REFERENCIAL = "Não foi possivel deletar pois o restaurante está em uso";
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private RestauranteAssembler restauranteAssembler;
    @Autowired
    private RestauranteDtoSingleSearchAssembler restauranteDtoSingleSearch;
    @Autowired
    FormaDePagamentoRepository formaDePagamentoRepository;
    @Autowired
    CozinhaRepository cozinhaRepository;


    @Transactional(readOnly = true)
    public CollectionModel<RestauranteDto> findAll() {
        return restauranteAssembler.toCollectionModel(restauranteRepository.getAll());
    }


    @Transactional(readOnly = true)
    public RestauranteDtoSingleSearch findById(Long id) {
        Restaurante restaurante = getRestauranteOrElseThrow(id);
        return restauranteDtoSingleSearch.toModel(restaurante);
    }

    @Transactional
    public RestauranteDto insert(RestauranteInsertDto dto) {
        Cozinha cozinha = getCozinhaOrElseThrow(dto);
        List<FormaDePagamento> formaDePagamentosObj = getFormaDePagamentosObj(dto);
        var restaurante = restauranteAssembler.toEntity(dto);
        return new RestauranteDto( restauranteRepository.save(restaurante));
    }



    @Transactional
    public void delete(Long id) {
        try {
            restauranteRepository.deleteById(getRestauranteOrElseThrow(id).getId());
            restauranteRepository.flush();
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(MSG_INTEGRIDADE_REFERENCIAL);
        }

    }

    @Transactional
    public RestauranteDto update(Long id, RestauranteUpdateDto dto) {
        Restaurante restaurante = getRestauranteOrElseThrow(id);
        restauranteAssembler.update(restaurante, dto);
        restaurante = restauranteRepository.save(restaurante);
        return restauranteAssembler.toModel(restaurante);

    }


    @Transactional
    public void desassociarFormaPagamento(Long restId, Long formaPagamanetoId) {
        Restaurante restaurante = getRestauranteOrElseThrow(restId);
        FormaDePagamento formaDePagamento = getFormaPagamentoOrElseThrow(formaPagamanetoId);

        restaurante.verificarAssociacaoFormaPagamento(formaDePagamento);
        restaurante.getFormasPagamento().remove(formaDePagamento);

    }

    @Transactional
    public void associarFormaPagamento(Long restId, Long formaPagamanetoId) {
        Restaurante restaurante = getRestauranteOrElseThrow(restId);
        FormaDePagamento novaFormaPagamentoObj = getFormaPagamentoOrElseThrow(formaPagamanetoId);
        restaurante.getFormasPagamento().add(novaFormaPagamentoObj);


    }

    @Transactional
    public RestauranteAbertoDto abertura(Long id) {
        Restaurante restaurante = getRestauranteOrElseThrow(id);
        restaurante.setAberto(true);
        return new RestauranteAbertoDto(restaurante.isAberto());
    }
    @Transactional
    public RestauranteAbertoDto fechamento(Long id) {
        Restaurante restaurante = getRestauranteOrElseThrow(id);
        restaurante.setAberto(false);
        return new RestauranteAbertoDto(restaurante.isAberto());
    }
    public Restaurante getRestauranteOrElseThrow(Long id) {
        return restauranteRepository.getId(id).orElseThrow(() ->
                new RestauranteNaoEncontradoException(MSG_RESTAURANTE_NAO_ENCONTRADO));
    }

    public List<FormaDePagamento> getFormaDePagamentosObj(RestauranteInsertDto dto) {
        List<Optional<FormaDePagamento>> formaDePagamentoList =
                formaDePagamentoRepository.getByIds(dto.getFormasDePagamento());

        return formaDePagamentoList.stream().map(
                x -> x.orElseThrow(() -> new RestauranteNaoEncontradoException(
                        MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA
                ))).toList();
    }
    public FormaDePagamento getFormaPagamentoOrElseThrow(Long formaPagamentoId) {
        return formaDePagamentoRepository.findById(formaPagamentoId).orElseThrow(() ->
                new FormaDePagamentoEncontradaException(MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA));
    }
    public Cozinha getCozinhaOrElseThrow(RestauranteInsertDto dto) {
        return cozinhaRepository.findById(dto.getCozinhaId()).orElseThrow(() ->
                new RestauranteNaoEncontradoException(MSG_COZINHA_NAO_ENCONTRADA));
    }


}
