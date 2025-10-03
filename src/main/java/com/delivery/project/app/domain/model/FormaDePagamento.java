package com.delivery.project.app.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_forma_de_pagamento")
@Data
public class FormaDePagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @ManyToOne()
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
}
