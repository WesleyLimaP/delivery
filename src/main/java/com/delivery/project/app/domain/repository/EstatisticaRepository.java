package com.delivery.project.app.domain.repository;

import com.delivery.project.app.api.model.projection.SomaTotVendasPorDiaQueryProjection;
import com.delivery.project.app.domain.model.Pedido;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface EstatisticaRepository extends org.springframework.data.repository.Repository<Pedido, Long> {


    @Query(nativeQuery = true, value = ("""
SELECT TB_PEDIDO.DATA_CRIACAO AS EMISSAO, COUNT(TB_PEDIDO.ID) AS PEDIDOS, SUM(TB_PEDIDO.VALOR_TOTAL) AS TOTAL
FROM TB_PEDIDO
WHERE (:restauranteId IS NULL OR TB_PEDIDO.RESTAURANTE_ID = :restauranteId)
AND TB_PEDIDO.STATUS LIKE 'ENTREGUE'
AND (:dataInicio IS NULL OR TB_PEDIDO.DATA_CRIACAO >= :dataInicio)
AND (:dataFim IS NULL OR TB_PEDIDO.DATA_CRIACAO <= :dataFim)
GROUP BY EMISSAO"""))
    List<SomaTotVendasPorDiaQueryProjection> getTotal(Long restauranteId, LocalDate dataInicio, LocalDate dataFim);
}
