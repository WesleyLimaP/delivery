package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.model.dto.filesDto.ImageDto;
import com.delivery.project.app.api.model.dto.fotoProduto.FotoProdutoDto;
import com.delivery.project.app.domain.service.FotoProdutoService;
import com.delivery.project.app.domain.service.FotoStorageService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos/{produtoId}/fotos")
public class RestauranteProdutoFotoController {
    @Autowired
    private FotoProdutoService service;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FotoProdutoDto> insert(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid ImageDto image) throws IOException {
        var response = service.insert(restauranteId, produtoId, image);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(response.getProdutoId())
                .toUri();

        return ResponseEntity.created(location).body(response);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotoProdutoDto> get(@PathVariable Long restauranteId, @PathVariable Long produtoId) throws IOException {
        var response = service.getFoto(restauranteId, produtoId);
        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        service.delete(restauranteId, produtoId);
        return ResponseEntity.noContent().build();
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<?> getImage(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestHeader(name = "Accept") String acceptHeacers) {

        var response = service.getFoto(restauranteId, produtoId);
        List<MediaType> mediaType = MediaType.parseMediaTypes(acceptHeacers);
        verifyMediaType(mediaType, MediaType.parseMediaType(response.getMediaType()));
        FotoStorageService.FotoRecuperada fotoRecuperada = service.recuperarFoto(response.getNomeArquivo());
        if (fotoRecuperada.temStream()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(response.getMediaType()))
                    .body(new InputStreamResource(fotoRecuperada.getStream()));

        } else {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                    .build();

        }
    }

    private void verifyMediaType(List<MediaType> mediaType, MediaType mediaTypeImage) throws HttpMediaTypeNotAcceptableException {
        mediaType.stream()
                .filter(mediaTypeImage::isCompatibleWith)
                .findFirst()
                .orElseThrow(() -> new HttpMediaTypeNotAcceptableException("Media type not supported"));
    }
}

