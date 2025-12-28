package com.delivery.project.app.api.model.dto.produtoDto.response;

import com.delivery.project.app.domain.model.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoMinResponseDto {
    private Long id;
    private String nome;
    private Double preco;
    public ProdutoMinResponseDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
}
