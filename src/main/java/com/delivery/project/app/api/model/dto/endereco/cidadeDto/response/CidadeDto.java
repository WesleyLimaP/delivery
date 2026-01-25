package com.delivery.project.app.api.model.dto.endereco.cidadeDto.response;

import com.delivery.project.app.domain.model.Cidade;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
public class CidadeDto extends RepresentationModel<CidadeDto> {
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private EstadoDto estado;

    public CidadeDto(Cidade entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.estado = new EstadoDto(entity.getEstado());
    }

    public CidadeDto(Long id, String nome, EstadoDto estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
}
