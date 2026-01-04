package com.delivery.project.app.core.email;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Data
@Validated
@Component
@ConfigurationProperties(prefix = "delivery.email.ses")
public class SesEmailConfiguration {
    @NotNull
    private String remetente;
}
