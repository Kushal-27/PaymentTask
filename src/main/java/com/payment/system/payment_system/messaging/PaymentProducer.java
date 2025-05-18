package com.payment.system.payment_system.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishPaymentInitiated(String event) {
        kafkaTemplate.send("payment_initiated", event);
    }
}
