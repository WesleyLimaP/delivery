package com.delivery.project.app.infra.service;

import com.delivery.project.app.api.model.dto.filesDto.ImageDto;
import com.delivery.project.app.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Service
public class FotoLocalStorageService implements FotoStorageService {
    @Value("${delivery.storage.local.foto}")
    private Path path;

    @Override
    public void armazenar(ImageDto image) {
        try {
            File file = new File(path.toUri());
            if (!file.exists()) file.mkdir();
            image.getFile().transferTo(path.resolve(Objects.requireNonNull(image.getFile().getOriginalFilename())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deletar(String uri) {
        try {
            Files.deleteIfExists(path.resolve(Path.of(uri)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public FotoStorageService.FotoRecuperada recuperar(String nomeArquivo) throws IOException {
        var fotoRecuperada = new FotoRecuperada();
       fotoRecuperada.setStream(Files.newInputStream(path.resolve(nomeArquivo)));
       return fotoRecuperada;
    }

}
