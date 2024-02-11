package com.Arthur224.BankSystem.service;

import com.Arthur224.BankSystem.dto.TransactionDetails;

public interface TransactionService {
    void saveTransaction(TransactionDetails transactionDetails);
}
