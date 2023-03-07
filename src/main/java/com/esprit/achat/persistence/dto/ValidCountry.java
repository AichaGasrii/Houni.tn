package com.esprit.achat.persistence.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CountryValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCountry {
    String message() default "Invalid country";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

