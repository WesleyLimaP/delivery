package com.delivery.project.app.core.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Configuration {
    @Autowired
    private StorageConfiguration storageConfiguration;

        @Bean
        public AmazonS3 getAmazonS3() {
            var credentials = new BasicAWSCredentials(
                    storageConfiguration.getS3().getIdChaveAcesso(),
                    storageConfiguration.getS3().getChaveSecreta());

            return AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(storageConfiguration.getS3().getLocal())
                    .build();
        }
}
