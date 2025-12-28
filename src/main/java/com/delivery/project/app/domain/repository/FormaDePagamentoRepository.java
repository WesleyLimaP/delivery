package com.delivery.project.app.domain.repository;

import com.delivery.project.app.domain.model.FormaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Repository
public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Long> {

    @Query(value = "SELECT obj FROM FormaDePagamento obj WHERE obj.id IN :formasDePagamento")
    List<Optional<FormaDePagamento>> getByIds(List<Long> formasDePagamento);
}
