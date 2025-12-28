package com.delivery.project.app.domain.repository.query;

import com.delivery.project.app.domain.model.FotoProduto;


public interface ProdutoRepositoryQueries {
      FotoProduto save(FotoProduto fotoProduto);
     void delete(FotoProduto fotoProduto);

}
