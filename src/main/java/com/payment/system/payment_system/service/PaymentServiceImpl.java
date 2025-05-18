package com.payment.system.payment_system.service;

import com.payment.system.payment_system.dto.PaymentRequest;
import com.payment.system.payment_system.messaging.PaymentProducer;
import com.payment.system.payment_system.model.Transaction;
import com.payment.system.payment_system.model.User;
import com.payment.system.payment_system.repo.TransactionRepository;
import com.payment.system.payment_system.repo.UserRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final UserRepository userRepository;

    @Override
    public Transaction processPayment(PaymentRequest request) {

        User user = getCurrentUser();

        Transaction tx = Transaction.builder()
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .status("INITIATED")
                .timestamp(new Date())
                .user(user)
                .paymentMethod(request.getPaymentMethod())
                .build();

        Transaction savedTx = transactionRepository.save(tx);
        paymentProducer.publishPaymentInitiated(savedTx.getTransactionId().toString());
        return savedTx;
    }

    @Override
    public List<Transaction> getTransactions(Long userId, String status, Date from, Date to) {
        var optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) throw new UsernameNotFoundException("User not found");
        return transactionRepository.findByUserAndStatusAndTimestampBetween(optionalUser.get() , status, from, to);
    }


    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
