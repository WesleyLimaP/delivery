package com.delivery.project.app.domain.repository.spec;

import com.delivery.project.app.api.model.filter.PedidoFilter;
import com.delivery.project.app.domain.model.Pedido;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class PedidoSpec {

    public static Specification<Pedido> findByFiltros(PedidoFilter filter) {
        return (root, query, cb) ->{
            var predicates = new ArrayList<jakarta.persistence.criteria.Predicate>();
            if(Pedido.class.equals(query.getResultType())){
                root.fetch("cliente");
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("itens").fetch("produto").fetch("restaurante").fetch("cozinha");
            };
            if(filter.getClienteId() != null){
                predicates.add(cb.equal(root.get("cliente").get("id"), filter.getClienteId()));
            }

            if(filter.getStatus() != null){
                predicates.add(cb.equal(root.get("status"), filter.getStatus().toUpperCase()));
            }

            if(filter.getRestauranteId() != null){
                predicates.add(cb.equal(root.get("restaurante").get("id"), filter.getRestauranteId()));
            }
            if(filter.getTaxaFrete() != null){
                predicates.add(cb.lessThanOrEqualTo(root.get("taxaFrete"), filter.getTaxaFrete()));
            }


            return cb.and(predicates.toArray(new Predicate[0]));
        };


    }
}
