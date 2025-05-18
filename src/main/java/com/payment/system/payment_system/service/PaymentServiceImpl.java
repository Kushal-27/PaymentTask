package com.payment.system.payment_system.service;

import com.payment.system.payment_system.dto.PaymentRequest;
import com.payment.system.payment_system.messaging.PaymentProducer;
import com.payment.system.payment_system.model.Transaction;
import com.payment.system.payment_system.repo.TransactionRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final TransactionRepository transactionRepository;
    private final PaymentProducer paymentProducer;

    @Override
    public Transaction processPayment(PaymentRequest request) {
        Transaction tx = Transaction.builder()
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .status("INITIATED")
                .timestamp(new Date())
                .userId(UUID.randomUUID())
                .paymentMethod(request.getPaymentMethod())
                .build();

        Transaction savedTx = transactionRepository.save(tx);
        paymentProducer.publishPaymentInitiated(savedTx.getTransactionId().toString());
        return savedTx;
    }

    @Override
    public List<Transaction> getTransactions(UUID userId, String status, Date from, Date to) {
        return transactionRepository.findByUserIdAndStatusAndTimestampBetween(userId, status, from, to);
    }
}
