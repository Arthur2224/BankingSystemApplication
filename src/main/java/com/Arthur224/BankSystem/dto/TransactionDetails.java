package com.Arthur224.BankSystem.dto;

import com.Arthur224.BankSystem.entity.User;
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
    private User idOfSourceAccount;
    private User idOfDestinationAccount;
    private String transactionType;
    private BigDecimal amount;


}
