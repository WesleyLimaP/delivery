package com.delivery.project.app.api.model.dto.estatisticaDto;

import com.delivery.project.app.api.model.projection.SomaTotVendasPorDiaQueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstatisticaDto {
    LocalDate emissao;
    Integer pedidos;
    Double total;

    public EstatisticaDto(SomaTotVendasPorDiaQueryProjection projection) {
        this.emissao = projection.getEmissao();
        this.pedidos = projection.getPedidos();
        this.total = projection.getTotal();
    }
}
