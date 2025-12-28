package com.delivery.project.app.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tb_item_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Integer quantidade;
    @Setter(AccessLevel.NONE)
    private  Double precoUnitario;
    @Setter(AccessLevel.NONE)
    private Double precoTotal;
    private String observacao;


    public Double getPrecoUnitario() {
        this.precoUnitario = produto.getPreco();
        return precoUnitario;
    }

    public Double getPrecoTotal() {
        this.precoTotal = getPrecoUnitario() * this.quantidade;
        return precoTotal;
    }
}
