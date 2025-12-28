package com.delivery.project.app.core.validation;

import com.delivery.project.app.api.model.dto.filesDto.ImageDto;
import com.delivery.project.app.core.anotations.ImageSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class ImageSizeValidator implements ConstraintValidator<ImageSize, MultipartFile> {
    private DataSize dataSize;
    @Override
    public void initialize(ImageSize constraintAnnotation) {
        dataSize = DataSize.parse(constraintAnnotation.max());
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || value.getSize() < dataSize.toBytes();
    }


}
