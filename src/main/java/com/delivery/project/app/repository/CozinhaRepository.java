package com.delivery.project.app.repository;

import com.delivery.project.app.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
}
