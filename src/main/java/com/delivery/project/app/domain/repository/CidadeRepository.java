package com.delivery.project.app.domain.repository;

import com.delivery.project.app.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
