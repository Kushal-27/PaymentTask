package com.payment.system.payment_system.model;

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

    @ManyToOne(fetch = FetchType.LAZY)  // or EAGER if you want the user loaded with the transaction
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String status;

    private Date timestamp;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
