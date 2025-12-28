package com.delivery.project.app.api.model.projection;

import java.time.LocalDate;

public interface SomaTotVendasPorDiaQueryProjection {
    LocalDate getEmissao();
    Integer getPedidos();
    Double getTotal();
}
