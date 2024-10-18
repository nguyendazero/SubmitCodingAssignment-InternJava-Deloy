package com.task_haibazo.validation.validator;

import com.task_haibazo.validation.annotation.ValidRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<ValidRange, Double> {

    private double min;
    private double max;

    @Override
    public void initialize(ValidRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value >= min && value <= max;
    }
}
