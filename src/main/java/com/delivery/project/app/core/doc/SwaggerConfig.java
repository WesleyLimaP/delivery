package com.delivery.project.app.core.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Delivery API")
                .version("1.0")
                .description("Api de delivery para restaurantes e seus clientes")
                .contact(new Contact().name("Wesley").url("todo")
                .email("wesleylima029@gmail.com")
                .name("Wesley")));


    }
}
