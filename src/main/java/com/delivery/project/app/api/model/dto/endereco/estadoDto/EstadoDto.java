package com.delivery.project.app.api.model.dto.endereco.estadoDto;

import com.delivery.project.app.domain.model.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
public class EstadoDto extends RepresentationModel<EstadoDto> {
    private Long id;
    @NotBlank
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
