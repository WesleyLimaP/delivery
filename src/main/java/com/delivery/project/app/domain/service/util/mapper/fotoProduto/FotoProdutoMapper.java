package com.delivery.project.app.domain.service.util.mapper.fotoProduto;

import com.delivery.project.app.api.model.dto.filesDto.ImageDto;
import com.delivery.project.app.domain.model.FotoProduto;
import com.delivery.project.app.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FotoProdutoMapper{
    @Autowired
    private ProdutoRepository repository;

    @Transactional(readOnly = true)
    public FotoProduto dtoToFotoProduto(Long produtoId, ImageDto imageDto) {
        return FotoProduto.builder()
                .tamanho((int) imageDto.getFile().getSize())
                .contentType(imageDto.getFile().getContentType())
                .descricao(imageDto.getDetails())
                .nomeArquivo(imageDto.getFile().getOriginalFilename())
                .produto(repository.findById(produtoId).get())
                .build();
    }
}
