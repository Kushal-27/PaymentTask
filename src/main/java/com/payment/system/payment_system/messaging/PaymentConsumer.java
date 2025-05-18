package com.payment.system.payment_system.messaging;

import com.payment.system.payment_system.repo.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {
    private final TransactionRepository transactionRepository;

    @KafkaListener(topics = "payment_initiated", groupId = "payment-group")
    public void consume(String message) throws InterruptedException {
        log.info("Received event: {}", message);
        Thread.sleep(3000); // simulate delay

        // Simulate status
        String[] statuses = {"SUCCESS", "PENDING", "FAILED"};
        String randomStatus = statuses[new Random().nextInt(statuses.length)];

        UUID transactionId = UUID.fromString(message);
        transactionRepository.findById(transactionId).ifPresent(tx -> {
            tx.setStatus(randomStatus);
            transactionRepository.save(tx);
            log.info("Updated transaction {} to status {}", transactionId, randomStatus);
        });
    }
}
