package com.delivery.project.app.dto.estadoDto;

import com.delivery.project.app.domain.model.Estado;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstadoDto {
    private Long id;
    private String nome;

    public EstadoDto(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
    }

    public EstadoDto(Long id) {
        this.id = id;
    }

    public EstadoDto(String nome) {
        this.nome = nome;
    }
}
