package com.delivery.project.app.domain.repository;

import com.delivery.project.app.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissoesRepository extends JpaRepository<Permissao, Long> {

}
