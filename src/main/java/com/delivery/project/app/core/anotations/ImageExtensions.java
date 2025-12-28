package com.delivery.project.app.core.anotations;


import com.delivery.project.app.core.validation.ImageExtensionValidator;
import com.delivery.project.app.core.validation.ImageSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ImageExtensionValidator.class})
public @interface ImageExtensions {
        String [] extensions();
        String message() default "a extensão não é permitida";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

}
