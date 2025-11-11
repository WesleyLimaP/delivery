package com.delivery.project.app.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_restaurante")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double taxaFrete;
    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;
    @OneToMany(mappedBy = "restaurante")
    private final List<FormaDePagamento> formasDePagamento = new ArrayList<>();
    @Embedded
    private Endereco endereco;
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

}
