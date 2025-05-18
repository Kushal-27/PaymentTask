package com.payment.system.payment_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;

    private UUID userId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String status;
    private Date timestamp;
    private PaymentMethod paymentMethod;
}
