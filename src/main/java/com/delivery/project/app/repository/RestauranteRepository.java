package com.delivery.project.app.repository;

import com.delivery.project.app.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    @Query(value = "SELECT DISTINCT obj from Restaurante obj JOIN FETCH obj.cozinha ")
    public List<Restaurante> getAll();

    @Query(value = "SELECT DISTINCT obj FROM Restaurante obj JOIN FETCH obj.cozinha JOIN FETCH obj.formasDePagamento WHERE obj.id = :id ")
    public Optional<Restaurante> getId(Long id);
}
