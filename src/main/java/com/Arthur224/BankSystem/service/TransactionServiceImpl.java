package com.Arthur224.BankSystem.service;

import com.Arthur224.BankSystem.dto.EnquiryRequest;
import com.Arthur224.BankSystem.dto.TransactionDetails;
import com.Arthur224.BankSystem.entity.Transaction;
import com.Arthur224.BankSystem.repository.TransactionRepository;
import com.Arthur224.BankSystem.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public void saveTransaction(TransactionDetails transactionDetails) {
        Transaction transaction =Transaction.builder()
                .transactionId(transactionDetails.getTransactionId())
                .transactionType(transactionDetails.getTransactionType())
                .destinationUser(transactionDetails.getIdOfDestinationAccount())
                .sourceUser(transactionDetails.getIdOfSourceAccount())
                .amount(transactionDetails.getAmount())
                .build();
       transactionRepository.save(transaction);
    }
    @Override
    public String dataEnquiry(EnquiryRequest request) {
        boolean isAccountExist=transactionRepository.existsByTransactionId(request.getAccountNumber());
        if(!isAccountExist){
            return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;
        }
        Transaction foundTransaction=transactionRepository.findByTransactionId(request.getAccountNumber());
        return foundTransaction.getTransactionId() + " "+ foundTransaction.getAmount();

    }

}
