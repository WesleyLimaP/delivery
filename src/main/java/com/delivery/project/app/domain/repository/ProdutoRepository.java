package com.delivery.project.app.domain.repository;

import com.delivery.project.app.domain.model.FotoProduto;
import com.delivery.project.app.domain.model.Produto;
import com.delivery.project.app.domain.repository.query.ProdutoRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {
    @Query(value = "select f from FotoProduto f join f.produto p join p.restaurante " +
            "where f.produto.id = :produtoId and p.restaurante.id = :restauranteId")
    Optional<FotoProduto> findFotoProduto(Long produtoId, Long restauranteId);
}
