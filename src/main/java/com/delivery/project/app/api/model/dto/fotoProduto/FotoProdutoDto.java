package com.delivery.project.app.api.model.dto.fotoProduto;

import com.delivery.project.app.domain.model.FotoProduto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FotoProdutoDto extends RepresentationModel<FotoProdutoDto> {
    @NotNull
    @Positive
    private Long produtoId;
    @NotNull
    private String nomeArquivo;
    @Max(30)
    private String descricao;
    @NotNull
    private String mediaType;
    @NotNull
    private int tamanho;

    public FotoProdutoDto(FotoProduto produtoEntity) {
        this.produtoId = produtoEntity.getId();
        this.nomeArquivo = produtoEntity.getNomeArquivo();
        this.descricao = produtoEntity.getDescricao();
        this.mediaType = produtoEntity.getContentType();
        this.tamanho = produtoEntity.getTamanho();
    }
}
