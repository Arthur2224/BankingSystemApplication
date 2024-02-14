package com.Arthur224.BankSystem.repository;

import com.Arthur224.BankSystem.entity.Transaction;
import com.Arthur224.BankSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Boolean existsByTransactionId(String transactionId);
    Transaction findByTransactionId(String transactionId);
    Transaction findByUser(User idOfSourceAccount);

}
