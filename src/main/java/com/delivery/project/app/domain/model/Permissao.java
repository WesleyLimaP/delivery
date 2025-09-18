package com.delivery.project.app.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_permissao")
@Data
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private String descricao;
}
