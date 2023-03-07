package com.esprit.achat.persistence.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class AdressValidator implements ConstraintValidator<ValidAdress, String> {

    private static final String ADDRESS_REGEX = "^[A-Za-z0-9\\'\\.\\-\\s\\,]+$";

    @Override
    public void initialize(ValidAdress constraintAnnotation) {
        // Nothing to initialize
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        // Perform the validation here
        return value.matches(ADDRESS_REGEX);
    }
}


