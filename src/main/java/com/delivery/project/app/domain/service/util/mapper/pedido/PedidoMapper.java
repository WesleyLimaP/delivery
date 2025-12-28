package com.delivery.project.app.domain.service.util.mapper.pedido;

import com.delivery.project.app.api.model.dto.pedidoDto.request.PedidoRequestDto;
import com.delivery.project.app.domain.model.*;
import com.delivery.project.app.domain.service.CidadeService;
import com.delivery.project.app.domain.service.util.AssossiationValidatorService;
import com.delivery.project.app.domain.service.util.FindOrFailService;
import com.delivery.project.app.domain.service.util.mapper.endereco.EnderecoMapper;
import com.delivery.project.app.domain.service.util.mapper.itemPedido.ItemPedidoMapper;
import com.delivery.project.app.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EnderecoMapper enderecoMapper;
    @Autowired
    private ItemPedidoMapper itemPedidoMapper;
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private AssossiationValidatorService assossiationValidatorService;
    @Autowired
    FindOrFailService findOrFailService;

    public Pedido toEntity(PedidoRequestDto dto){
        Pedido pedido = new Pedido();
        pedido.setCliente(usuarioRepository.findById(1L).get());
        pedido.setEndereco(enderecoMapper.toEntity(dto.getEndereco()));
        pedido.setStatusCriado();
        pedido.setRestaurante(findOrFailService.getRestauranteOrElseThrow(dto.getRestaurante().id()));
        pedido.getItens().addAll(dto.getItens().stream().map(x -> itemPedidoMapper.toEntity(x, pedido)).toList());
        assossiationValidatorService.verificarProdutoAssociado(pedido.getRestaurante(), pedido.getItens().stream().map(ItemPedido::getProduto).toList());
        pedido.getValorTotal();
        pedido.setFormaDePagamento(findOrFailService.getFormaPagamentoOrElseThrow(dto.getFormaDePagamento().id()));
        assossiationValidatorService.verificarAssociacaoFormaPagamento(pedido.getRestaurante(), pedido.getFormaDePagamento());
        return pedido;

    }

}
