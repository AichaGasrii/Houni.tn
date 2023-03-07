package com.esprit.achat.persistence.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryValidator implements ConstraintValidator<ValidCountry, String> {

    public void initialize(ValidCountry constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // Perform the validation here
        return isValidCountry(value);
    }

    private boolean isValidCountry(String value) {
        // Call the RestCountries API to validate the country
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity(
                    "https://restcountries.com/v3.1/name/{name}?fullText=true",
                    Object[].class,
                    value);
            return true;
        } catch (HttpClientErrorException.NotFound ex) {
            return false;
        }
    }
}
