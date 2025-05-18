package com.payment.system.payment_system.dto;

import com.payment.system.payment_system.model.Currency;
import com.payment.system.payment_system.model.PaymentMethod;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class PaymentRequest {
    @NotNull
    private Double amount;

    @NotNull
    private Currency currency;

    @NotBlank
    private String customerName;

    @Email
    private String customerEmail;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotBlank
    private String cardOrAccountDetails;
}
