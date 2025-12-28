package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.model.dto.filesDto.ImageDto;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public interface FotoStorageService {
    void armazenar(ImageDto image);
    void deletar(String uri);
    FotoRecuperada recuperar(String nomeArquivo) throws IOException;

    @Data
    class FotoRecuperada{
        private InputStream stream;
        private String url;

        public boolean temStream() {
            return stream != null;
        }
        public boolean temUrl() {
            return url != null;
        }
    }
}
