package com.esprit.achat.persistence.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

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
        // Implement the logic to check if the country is valid
        // You can use a third-party library or a web service to perform this check
        // For example, you can use the RestCountries API to check if the country exists:
        // https://restcountries.com/v3.1/name/{name}?fullText=true
        // If the country exists, the API will return a JSON response with information about the country.
        // If the country doesn't exist, the API will return a 404 Not Found response.
        // Here's an example of how you can use the RestTemplate to call the API:
        // RestTemplate restTemplate = new RestTemplate();
        // try {
        //     ResponseEntity<Object[]> response = restTemplate.getForEntity(
        //         "https://restcountries.com/v3.1/name/{name}?fullText=true",
        //         Object[].class,
        //         value);
        //     return response.getStatusCode() == HttpStatus.OK;
        // } catch (HttpClientErrorException.NotFound ex) {
        //     return false;
        // }
        // You can customize this code to fit your application's needs.
        // This is just an example to demonstrate the validation logic.
        return true; // For now, assume all countries are valid
    }
}
