package com.payment.system.payment_system.annotation;

import com.payment.system.payment_system.annotation.validator.ImarkEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = ImarkEmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ImarkEmail {
    String message() default "Email must end with @imark.com.np";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
