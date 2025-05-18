package com.payment.system.payment_system.service;

import com.payment.system.payment_system.dto.PaymentRequest;
import com.payment.system.payment_system.model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PaymentService {

    Transaction processPayment(PaymentRequest request);
    List<Transaction> getTransactions(Long userId, String status, Date from, Date to);
}
