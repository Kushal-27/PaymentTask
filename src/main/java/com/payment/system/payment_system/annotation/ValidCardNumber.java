package com.payment.system.payment_system.annotation;

import com.payment.system.payment_system.annotation.validator.CardNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CardNumberValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCardNumber {
    String message() default "Invalid card number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
