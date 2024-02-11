package com.Arthur224.BankSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetails {
    private String transactionId;
    private String idOfSourceAccount;
    private String idOfDestinationAccount;
    private String transactionType;
    private BigDecimal amount;


}
