package com.task_haibazo.validation.validator;

import com.task_haibazo.validation.annotation.NotAm;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotAmValidator implements ConstraintValidator<NotAm, Double> {

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value >= 0;
    }
}
