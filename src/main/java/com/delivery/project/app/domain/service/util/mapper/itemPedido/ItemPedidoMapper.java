package com.delivery.project.app.domain.service.util.mapper.itemPedido;

import com.delivery.project.app.api.model.dto.itemPedidoDto.request.ItemPedidoRequestDto;
import com.delivery.project.app.domain.model.ItemPedido;
import com.delivery.project.app.domain.model.Pedido;
import com.delivery.project.app.domain.service.util.FindOrFailService;
import com.delivery.project.app.domain.service.util.mapper.produto.ProdutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {
    @Autowired
    ProdutoMapper produtoMapper;
    @Autowired
    private FindOrFailService findOrFailService;
    public ItemPedido toEntity(ItemPedidoRequestDto dto, Pedido pedido){
        ItemPedido item = new ItemPedido();
        item.setObservacao(dto.getObservacao());
        item.setQuantidade(dto.getQuantidade());
        item.setPedido(pedido);
        item.setProduto(findOrFailService.getProdutoOrElseThrow(dto.getProduto().id()));
        item.getPrecoTotal();

        return item;
    }
}
