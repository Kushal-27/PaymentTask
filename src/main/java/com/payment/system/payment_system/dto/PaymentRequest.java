package com.payment.system.payment_system.dto;

import com.payment.system.payment_system.annotation.ImarkEmail;
import com.payment.system.payment_system.annotation.ValidCardNumber;
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

    @ImarkEmail
    private String customerEmail;

    @NotNull
    private PaymentMethod paymentMethod;

    @ValidCardNumber
    private String cardOrAccountDetails;
}
