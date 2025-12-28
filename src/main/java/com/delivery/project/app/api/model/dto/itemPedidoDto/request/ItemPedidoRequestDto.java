package com.delivery.project.app.api.model.dto.itemPedidoDto.request;

import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestIdDto;
import com.delivery.project.app.domain.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoRequestDto {
    private Integer quantidade;
    private ProdutoRequestIdDto produto;
    private String observacao;
}
