package com.delivery.project.app.api.model.dto.filesDto;

import com.delivery.project.app.core.anotations.ImageExtensions;
import com.delivery.project.app.core.anotations.ImageSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageDto {
    @NotNull
    @ImageSize(max = "10000KB")
    @ImageExtensions(extensions = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile file;
    private String details;

}
