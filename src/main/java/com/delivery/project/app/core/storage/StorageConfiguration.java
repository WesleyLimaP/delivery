package com.delivery.project.app.core.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "delivery.storage")
@Data
public class StorageConfiguration {

    private Local local;
    private S3 s3;

    @Data
    public static class Local {
        private String foto;
    }

    @Data
    public static class S3 {
        private String idChaveAcesso;
        private String chaveSecreta;
        private String bucket;
        private String local;
        private String diretorio;
    }
}