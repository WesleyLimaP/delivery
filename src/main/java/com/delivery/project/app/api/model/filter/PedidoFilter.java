package com.delivery.project.app.api.model.filter;

import com.delivery.project.app.domain.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class PedidoFilter {
    private Long clienteId;
    private Double taxaFrete;
    private String status;
    private Long restauranteId;
}
