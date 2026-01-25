package com.delivery.project.app.domain.model;

import com.delivery.project.app.domain.exceptions.AssociacaoException;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_restaurante")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean aberto;
    private Double taxaFrete;
    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;
    @ManyToMany()
    @JoinTable(
            name = "tb_restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
    )
    private Set<FormaDePagamento> formasPagamento = new HashSet<>();
    @Embedded
    private Endereco endereco;
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "tb_usuario_restaurante",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios = new HashSet<>();

    public boolean verificarProdutoAssociado(Produto produtoRepo) {
        return this.getProdutos().contains(produtoRepo);
    }

    public boolean verificarProdutoAssociado(List<Produto> produtoRepo) {
        return new HashSet<>(this.getProdutos()).containsAll(produtoRepo);

    }

    public boolean verificarAssociacaoFormaPagamento(FormaDePagamento formaDePagamento) {
        return this.getFormasPagamento().contains(formaDePagamento);
    }
}

