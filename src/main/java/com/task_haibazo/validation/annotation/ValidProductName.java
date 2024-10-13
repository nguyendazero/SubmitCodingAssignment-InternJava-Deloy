package com.task_haibazo.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

import com.task_haibazo.validation.validator.ProductNameValidator;

@Documented
@Constraint(validatedBy = ProductNameValidator.class) // Gắn với class validator
@Target({ ElementType.METHOD, ElementType.FIELD }) // Áp dụng cho method hoặc field
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProductName {
    String message() default "Tên sản phẩm không hợp lệ"; 
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}