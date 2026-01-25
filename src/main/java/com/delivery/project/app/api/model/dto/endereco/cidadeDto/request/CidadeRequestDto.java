package com.delivery.project.app.api.model.dto.endereco.cidadeDto.request;

import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.request.EstadoIdDto;
import com.delivery.project.app.domain.model.Cidade;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
public class CidadeRequestDto extends RepresentationModel<CidadeRequestDto> {
    private String nome;
    @NotNull
    private EstadoDto estado;

    public CidadeRequestDto(Cidade entity) {
        this.nome = entity.getNome();
        this.estado = new EstadoDto(entity.getEstado().getId());
    }

    public CidadeRequestDto(String nome, EstadoDto estado) {
        this.nome = nome;
        this.estado = estado;
    }
}
