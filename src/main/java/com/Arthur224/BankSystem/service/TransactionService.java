package com.Arthur224.BankSystem.service;

import com.Arthur224.BankSystem.dto.EnquiryRequest;
import com.Arthur224.BankSystem.dto.TransactionDetails;

public interface TransactionService {
    void saveTransaction(TransactionDetails transactionDetails);
    String dataEnquiry(EnquiryRequest request);
}
