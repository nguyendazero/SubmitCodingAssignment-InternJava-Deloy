package com.task_haibazo.validation.annotation;

import com.task_haibazo.validation.validator.RangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RangeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRange {
    String message() default "Giá trị không hợp lệ";
    
    double min() default 0;
    double max() default Double.MAX_VALUE;
    
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
