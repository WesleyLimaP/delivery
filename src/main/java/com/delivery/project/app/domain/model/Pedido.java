package com.delivery.project.app.domain.model;

import com.delivery.project.app.exceptions.StatusPedidoException;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter(AccessLevel.NONE)
    private Double subTotal;
    @Setter(AccessLevel.NONE)
    private Double taxaFrete;
    @Setter(AccessLevel.NONE)
    private Double valorTotal;
    @CreationTimestamp
    @Setter(AccessLevel.PRIVATE)
    private LocalDate dataCriacao;
    private LocalDate dataConfirmacao;
    private LocalDate dataCancelamento;
    private LocalDate dataEntrega;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
    @Embedded
    private Endereco endereco;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formas_de_pagamento_id")
    private FormaDePagamento formaDePagamento;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private final List<ItemPedido> itens = new ArrayList<>();

    public Double getValorTotal() {
        this.valorTotal = getSubTotal() + getTaxaFrete();
        return valorTotal;
    }

    public Double getTaxaFrete() {
        this.taxaFrete = restaurante.getTaxaFrete();
        return taxaFrete;
    }

    public Double getSubTotal() {
        this.subTotal = this.itens.stream().map(ItemPedido::getPrecoTotal).reduce(Double::sum).orElseThrow(() ->
                new RuntimeException("nao foi possivel fazer a soma"));
        return subTotal;
    }
    public void alterarStatus(StatusPedido status){
        verificarStatusAnterior(this, status);
        this.setStatus(status);
    }
    private void verificarStatusAnterior(Pedido pedido, StatusPedido status){
        if (!status.getStatusAnterior().contains(pedido.status)){
             throw new StatusPedidoException("Nao é possivel alterar de " + pedido.getStatus().getDescricao() + " para " +  StatusPedido.CONFIRMADO.getDescricao());
        };
    }

    public void setStatusCriado(){
        if(this.getStatus() == null){
            this.setStatus(StatusPedido.CRIADO);
        }
    }


}
