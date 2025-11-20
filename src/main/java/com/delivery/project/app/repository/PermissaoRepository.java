package com.delivery.project.app.repository;

import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
