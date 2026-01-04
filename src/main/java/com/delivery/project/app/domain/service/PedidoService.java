package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.model.dto.pedidoDto.request.PedidoRequestDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoMaxDto;
import com.delivery.project.app.api.model.filter.PedidoFilter;
import com.delivery.project.app.core.email.SesEmailConfiguration;
import com.delivery.project.app.domain.model.*;
import com.delivery.project.app.domain.service.util.FindOrFailService;
import com.delivery.project.app.domain.service.util.mapper.pedido.PedidoMapper;
import com.delivery.project.app.domain.repository.PedidoRepository;
import com.delivery.project.app.domain.repository.spec.PedidoSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService  {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    FindOrFailService findOrFailService;
    @Autowired
    private TransactionalEmailService emailService;



    @Transactional(readOnly = true)
public Page<List<PedidoDto>> findAll(PedidoFilter filter, Pageable pageable) {
        Page<Pedido> pedidoPage = repository.findAll(PedidoSpec.findByFiltros(filter), pageable);
        return pedidoPage.map(x -> List.of(new PedidoDto(x)));

    }

    @Transactional(readOnly = true)
    public PedidoMaxDto findById(Long id) {
        return new PedidoMaxDto(findOrFailService.getPedidoOrElseThrow(id));
    }


    @Transactional
    public PedidoMaxDto insert(PedidoRequestDto dto) {
        Pedido pedido = pedidoMapper.toEntity(dto);
        return  new PedidoMaxDto(repository.save(pedido));
    }
    @Transactional
    public PedidoMaxDto UpdateStatusToConfirmado( Long pedidoId) {
        Pedido pedido = findOrFailService.getPedidoOrElseThrow(pedidoId);
        pedido.confirmar();
        pedido.setDataConfirmacao(LocalDate.now());
        return new PedidoMaxDto(repository.save(pedido));

    }
    @Transactional
    public PedidoMaxDto UpdateStatusToCancelado( Long pedidoId) {
        Pedido pedido = findOrFailService.getPedidoOrElseThrow(pedidoId);
        pedido.cancelar();
        pedido.setDataCancelamento(LocalDate.now());
        return  new PedidoMaxDto(repository.save(pedido));
    }
    @Transactional
    public PedidoMaxDto UpdateStatusToEntregue( Long pedidoId) {
        Pedido pedido = findOrFailService.getPedidoOrElseThrow(pedidoId);
        pedido.entregar();
        pedido.setDataEntrega(LocalDate.now());
        return  new PedidoMaxDto(repository.save(pedido));
    }


}
