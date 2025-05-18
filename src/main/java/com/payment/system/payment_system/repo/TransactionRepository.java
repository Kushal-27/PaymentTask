package com.payment.system.payment_system.repo;

import com.payment.system.payment_system.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByUserIdAndStatusAndTimestampBetween(UUID userId, String status, Date from, Date to);
}

