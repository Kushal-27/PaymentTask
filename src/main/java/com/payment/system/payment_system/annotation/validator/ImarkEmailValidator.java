package com.payment.system.payment_system.annotation.validator;

import com.payment.system.payment_system.annotation.ImarkEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImarkEmailValidator implements ConstraintValidator<ImarkEmail, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.endsWith("@imark.com.np");
    }
}
