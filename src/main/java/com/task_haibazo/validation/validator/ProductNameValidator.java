package com.task_haibazo.validation.validator;

import com.task_haibazo.validation.annotation.ValidProductName;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductNameValidator implements ConstraintValidator<ValidProductName, String> {

    @Override
    public boolean isValid(String productName, ConstraintValidatorContext context) {
        if (productName == null) {
            return false; 
        }
        return !productName.matches(".*\\d.*");
    }
}
