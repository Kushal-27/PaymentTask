package com.payment.system.payment_system.service;

import com.payment.system.payment_system.dto.PaymentRequest;
import com.payment.system.payment_system.model.Transaction;
import com.payment.system.payment_system.model.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PaymentService {

    Transaction processPayment(PaymentRequest request, User user);
    List<Transaction> getTransactions(Long userId, String status, Date from, Date to);
}
