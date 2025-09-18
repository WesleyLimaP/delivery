package com.delivery.project.app.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Cozinha cozinha;
    @OneToMany(mappedBy = "restaurante")
    private List<FormaDePagamento> formasDePagamento;

}
