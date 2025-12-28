package com.delivery.project.app.infra.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.delivery.project.app.api.model.dto.filesDto.ImageDto;
import com.delivery.project.app.core.storage.StorageConfiguration;
import com.delivery.project.app.domain.service.FotoStorageService;
import com.delivery.project.app.exceptions.AmazonS3StorageException;
import net.sf.jasperreports.repo.InputStreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.logging.StreamHandler;

@Service
@Primary
public class S3StorageService implements FotoStorageService {
    @Autowired
    private StorageConfiguration storageConfiguration;
    @Autowired
    private AmazonS3 s3;
    @Override
    public void armazenar(ImageDto image) {
        try {
            var metadata = new ObjectMetadata();
            metadata.setContentLength(image.getFile().getSize());
            metadata.setContentType(image.getFile().getContentType());
            var path = storageConfiguration.getS3().getDiretorio() + "/" + image.getFile().getOriginalFilename();
            var putObjectRequest = new PutObjectRequest(
                    storageConfiguration.getS3().getBucket(),
                   path,
                    image.getFile().getInputStream(), metadata);
            s3.putObject(putObjectRequest);
        } catch (IOException e) {
            throw new AmazonS3StorageException("Erro ao armazenar arquivo no S3");
        }

    }

    @Override
    public void deletar(String uri) {
        try {
            var path = storageConfiguration.getS3().getDiretorio() + "/" + uri;
            var deleteRequest = new DeleteObjectRequest(storageConfiguration.getS3().getBucket(), path);
            s3.deleteObject(deleteRequest);
        } catch (AmazonClientException e) {
            throw new AmazonS3StorageException("Erro ao deletar arquivo no S3");
        }

    }

    @Override
    public FotoStorageService.FotoRecuperada recuperar(String nomeArquivo){
            var path = storageConfiguration.getS3().getDiretorio() + "/" + nomeArquivo;
            URL url = s3.getUrl(storageConfiguration.getS3().getBucket(), path);
            FotoRecuperada fotoRecuperada = new FotoRecuperada();
            fotoRecuperada.setUrl(url.toString());
            return fotoRecuperada;
    }
}
