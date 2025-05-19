package com.payment.system.payment_system.controller;

import com.payment.system.payment_system.auth.LoginValidator;
import com.payment.system.payment_system.dto.PaymentRequest;
import com.payment.system.payment_system.model.Transaction;
import com.payment.system.payment_system.model.User;
import com.payment.system.payment_system.service.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.config.annotation.web.SecurityMarker;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {

    private final PaymentService paymentService;
    private final LoginValidator loginValidator;

    @PostMapping("/process")
    public Transaction process(@Valid @RequestBody PaymentRequest request) {
        var user = loginValidator.validateUser();
        return paymentService.processPayment(request, user);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions(
            @RequestParam Long userId,
            @RequestParam String status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to) {
        loginValidator.validateUser();
        return paymentService.getTransactions(userId, status, from, to);
    }
}
