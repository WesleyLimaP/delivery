package com.delivery.project.app.domain.service.util;

import com.delivery.project.app.domain.model.FormaDePagamento;
import com.delivery.project.app.domain.model.Produto;
import com.delivery.project.app.domain.model.Restaurante;
import com.delivery.project.app.domain.exceptions.AssociacaoException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssossiationValidatorService {

    public void verificarProdutoAssociado(Restaurante restaurante, Produto produtoRepo) {
        if(!restaurante.getProdutos().contains(produtoRepo)) {
            throw new AssociacaoException("produto informado nao pertence a este restaurante");
        }
    }
    public void verificarProdutoAssociado(Restaurante restaurante, List<Produto> produtoRepo) {
        for(Produto produto : produtoRepo){
            if(!restaurante.getProdutos().contains(produto)){
                throw new AssociacaoException("o produto nao pertence a esse restaurante");
            }
        }
    }
    public void verificarAssociacaoFormaPagamento(Restaurante restaurante, FormaDePagamento formaDePagamento) {
        if(!restaurante.getFormasPagamento().contains(formaDePagamento)){
            throw new AssociacaoException("a forma de pagamento a ser mudada nao pertence a este restaurantw");
        }
    }
}
