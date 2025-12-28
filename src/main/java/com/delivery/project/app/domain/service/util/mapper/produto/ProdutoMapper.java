package com.delivery.project.app.domain.service.util.mapper.produto;

import com.delivery.project.app.api.model.dto.produtoDto.request.ProdutoRequestDto;
import com.delivery.project.app.domain.model.Produto;
import com.delivery.project.app.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    //update
    public void toEntity(ProdutoRequestDto dto, Produto produto){
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setAtivo(dto.isAtivo());
        produto.setPreco(dto.getPreco());
    }
    //merge para insert
    public Produto toEntity(ProdutoRequestDto dto, Restaurante restaurante){
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setAtivo(dto.isAtivo());
        produto.setPreco(dto.getPreco());
        produto.setRestaurante(restaurante);
        return produto;
    }
}
