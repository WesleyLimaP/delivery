package com.delivery.project.app.api.model.dto.cidadeDto;

import com.delivery.project.app.domain.model.Cidade;
import com.delivery.project.app.api.model.dto.estadoDto.EstadoDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidadeDto {
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private EstadoDto estadoDto;

    public CidadeDto(Cidade entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.estadoDto = new EstadoDto(entity.getEstado());
    }

    public CidadeDto(EstadoDto estadoDto, String nome) {
        this.estadoDto = estadoDto;
        this.nome = nome;
    }
}
