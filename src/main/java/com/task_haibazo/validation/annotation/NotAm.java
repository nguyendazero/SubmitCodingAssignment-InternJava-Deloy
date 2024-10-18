package com.task_haibazo.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

import com.task_haibazo.validation.validator.NotAmValidator;

@Documented
@Constraint(validatedBy = NotAmValidator.class) 
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotAm {
    String message() default "Không được nhỏ hơn 0"; 
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}