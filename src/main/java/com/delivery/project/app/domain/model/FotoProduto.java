package com.delivery.project.app.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_foto_produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FotoProduto {
    @Id
    @Column(name = "produtoId")
    private Long id;
    private String descricao;
    private String nomeArquivo;
    private String contentType;
    private Integer tamanho;
    @OneToOne
    @MapsId
    private Produto produto;
}
