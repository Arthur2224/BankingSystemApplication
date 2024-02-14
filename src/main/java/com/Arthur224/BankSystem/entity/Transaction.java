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
@Data
@Table(name ="transactions")
public class Transaction {
    @Id
    private String transactionId;
    @CreationTimestamp
    private LocalDateTime dateTime;
    private String transactionType;
    private BigDecimal amount;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "source_account_number")
    private User sourceUser;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "destination_account_number")
    private User destinationUser;


}
