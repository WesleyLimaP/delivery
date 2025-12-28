package com.delivery.project.app.core.validation;

import com.delivery.project.app.core.anotations.ImageExtensions;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ImageExtensionValidator implements ConstraintValidator<ImageExtensions, MultipartFile>{
    String [] extensions;
    @Override
    public void initialize(ImageExtensions constraintAnnotation) {
        extensions = constraintAnnotation.extensions();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile.isEmpty()) return false;
        List<String> extensionsList = Arrays.asList(extensions);
        return extensionsList.contains(multipartFile.getContentType());
    }
}
