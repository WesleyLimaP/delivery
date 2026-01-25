package com.delivery.project.app.api.model.dto.produtoDto.response;

import com.delivery.project.app.domain.model.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoResponseDto extends RepresentationModel<ProdutoResponseDto> {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private boolean ativo;

    public ProdutoResponseDto(Produto produto) {
        this.ativo = produto.isAtivo();
        this.descricao = produto.getDescricao();
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
}
