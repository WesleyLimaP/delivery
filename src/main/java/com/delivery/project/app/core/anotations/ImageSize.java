package com.delivery.project.app.core.anotations;

import com.delivery.project.app.core.validation.ImageSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ImageSizeValidator.class})
public @interface ImageSize {
    String max();
    String message() default "O tamanho da imagem é superior ao tmanho permitio";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
