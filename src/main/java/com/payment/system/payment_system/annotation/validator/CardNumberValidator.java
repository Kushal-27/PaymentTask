package com.payment.system.payment_system.annotation.validator;

import com.payment.system.payment_system.annotation.ValidCardNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardNumberValidator implements ConstraintValidator<ValidCardNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("^\\d{4}([- ]?)\\d{4}\\1\\d{4}\\1\\d{4}$");
    }

}
