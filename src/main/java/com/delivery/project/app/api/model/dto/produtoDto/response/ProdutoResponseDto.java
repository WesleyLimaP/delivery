package com.delivery.project.app.api.model.dto.produtoDto.response;

import com.delivery.project.app.domain.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDto extends RepresentationModel<ProdutoResponseDto> {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private boolean ativo;


}
