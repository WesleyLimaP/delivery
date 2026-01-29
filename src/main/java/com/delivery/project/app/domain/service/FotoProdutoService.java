package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.model.dto.filesDto.ImageDto;
import com.delivery.project.app.api.model.dto.fotoProduto.FotoProdutoDto;
import com.delivery.project.app.api.model.mapper.FotoProdutoMapper;
import com.delivery.project.app.domain.repository.ProdutoRepository;
import com.delivery.project.app.domain.exceptions.FotoNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class FotoProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private FotoStorageService storageService;
    @Autowired
    private FotoProdutoMapper assembler;

    @Transactional
    public FotoProdutoDto insert(Long restauranteId, Long produtoId, ImageDto imageDto){
        var fotoProduto = repository.findFotoProduto(produtoId, restauranteId);

        fotoProduto.ifPresent(foto -> repository.delete(foto));
        fotoProduto.ifPresent(foto -> storageService.deletar(foto.getNomeArquivo()));

        var produtoEntity = assembler.toEntity(imageDto);

        FotoProdutoDto dto = new FotoProdutoDto(repository.save(produtoEntity));

        storageService.armazenar(imageDto);
        return dto;


    }

    @Transactional(readOnly = true)
    public FotoProdutoDto getFoto(Long restauranteId, Long produtoId) {
        restauranteService.findById(restauranteId);
        repository.findById(produtoId);
        var object = repository.findFotoProduto(produtoId, restauranteId).orElseThrow(() ->
                new FotoNaoEncontradaException("foto nao encotrada"));
        return assembler.toModel(object);
    }

    @Transactional(readOnly = true)
    public FotoStorageService.FotoRecuperada recuperarFoto(String nomeArquivo) throws IOException {
        return storageService.recuperar(nomeArquivo);
    }

    @Transactional()
    public void delete(Long restauranteId, Long produtoId) {
        var object = repository.findFotoProduto(produtoId, restauranteId).orElseThrow(() ->
                new FotoNaoEncontradaException("foto nao encotrada"));
        repository.delete(object);
        storageService.deletar(object.getNomeArquivo()
        );
    }
}
