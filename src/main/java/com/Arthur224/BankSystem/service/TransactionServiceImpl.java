package com.Arthur224.BankSystem.service;

import com.Arthur224.BankSystem.dto.TransactionDetails;
import com.Arthur224.BankSystem.entity.Transaction;
import com.Arthur224.BankSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public void saveTransaction(TransactionDetails transactionDetails) {
        Transaction transaction =Transaction.builder()
                .transactionId(transactionDetails.getTransactionId())
                .transactionType(transactionDetails.getTransactionType())
                .idOfDestinationAccount(transactionDetails.getIdOfDestinationAccount())
                .idOfSourceAccount(transactionDetails.getIdOfSourceAccount())
                .amount(transactionDetails.getAmount())
                .build();
       transactionRepository.save(transaction);
    }
}
