package com.delivery.project.app.api.model.dto.itemPedidoDto.response;

import com.delivery.project.app.api.model.dto.produtoDto.response.ProdutoMinResponseDto;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class ItemPedidoResponseDto extends RepresentationModel<ItemPedidoResponseDto> {
    private Long id;
    private Integer quantidade;
    private ProdutoMinResponseDto produto;
    private  Double precoUnitario;
    private Double precoTotal;
    private String observacao;

}
