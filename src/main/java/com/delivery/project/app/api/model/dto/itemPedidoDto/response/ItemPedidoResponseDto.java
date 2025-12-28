package com.delivery.project.app.api.model.dto.itemPedidoDto.response;

import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoMinResponseDto;
import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoResponseDto;
import com.delivery.project.app.domain.model.ItemPedido;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemPedidoResponseDto {
    private Long id;
    private Integer quantidade;
    private ProdutoMinResponseDto produto;
    private  Double precoUnitario;
    private Double precoTotal;
    private String observacao;

    public ItemPedidoResponseDto(ItemPedido x) {
        this.id = x.getId();
        this.quantidade = x.getQuantidade();
        this.precoUnitario = x.getPrecoUnitario();
        this.precoTotal = x.getPrecoTotal();
        this.observacao = x.getObservacao();
        this.produto = new ProdutoMinResponseDto(x.getProduto());
    }
}
