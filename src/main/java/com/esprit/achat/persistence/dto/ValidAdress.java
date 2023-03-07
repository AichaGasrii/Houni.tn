package com.esprit.achat.persistence.dto;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

@Target({ FIELD, METHOD, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { AdressValidator.class })
@Documented
public @interface ValidAdress {
    String message() default "Invalid address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
