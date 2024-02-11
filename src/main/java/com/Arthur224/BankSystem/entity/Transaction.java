package com.Arthur224.BankSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="transactions")
public class Transaction {
    @Id
    private String transactionId;
    @CreationTimestamp
    private LocalDateTime dateTime;
    private String idOfSourceAccount;
    private String idOfDestinationAccount;
    private String transactionType;
    private BigDecimal amount;


}
