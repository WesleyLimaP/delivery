package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.assembler.PedidoAssembler;
import com.delivery.project.app.api.assembler.PedidoMaxAssembler;
import com.delivery.project.app.api.model.dto.pedidoDto.request.PedidoRequestDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoMaxDto;
import com.delivery.project.app.api.model.filter.PedidoFilter;
import com.delivery.project.app.domain.exceptions.PedidoNaoEncontradoException;
import com.delivery.project.app.domain.model.*;
import com.delivery.project.app.domain.repository.PedidoRepository;
import com.delivery.project.app.domain.repository.spec.PedidoSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PedidoService  {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private PedidoAssembler assembler;
    @Autowired
    private PedidoMaxAssembler maxAssembler;
    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;



    @Transactional(readOnly = true)
public PagedModel<PedidoDto> findAll(PedidoFilter filter, Pageable pageable) {
        Page<Pedido> pedidoPage = repository.findAll(PedidoSpec.findByFiltros(filter), pageable);
        return pagedResourcesAssembler.toModel(pedidoPage, assembler);

    }

    @Transactional(readOnly = true)
    public PedidoMaxDto findById(Long id) {
        return maxAssembler.toModel(getPedidoOrElseThrow(id));
    }


    @Transactional
    public PedidoMaxDto insert(PedidoRequestDto dto) {
       var pedido = assembler.toEntity(dto);
       pedido = repository.save(pedido);
       return maxAssembler.toModel(pedido);
    }
    @Transactional
    public PedidoMaxDto UpdateStatusToConfirmado( Long pedidoId) {
        Pedido pedido = getPedidoOrElseThrow(pedidoId);
        pedido.confirmar();
        pedido.setDataConfirmacao(LocalDate.now());
        return maxAssembler.toModel(pedido);

    }
    @Transactional
    public PedidoMaxDto UpdateStatusToCancelado( Long pedidoId) {
        Pedido pedido = getPedidoOrElseThrow(pedidoId);
        pedido.cancelar();
        pedido.setDataCancelamento(LocalDate.now());
        return maxAssembler.toModel(pedido);
    }
    @Transactional
    public PedidoMaxDto UpdateStatusToEntregue( Long pedidoId) {
        Pedido pedido = getPedidoOrElseThrow(pedidoId);
        pedido.entregar();
        pedido.setDataEntrega(LocalDate.now());
        return maxAssembler.toModel(pedido);
    }
    public Pedido getPedidoOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException("pedido nao encontrado"));
    }


}
