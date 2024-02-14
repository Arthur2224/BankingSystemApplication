package com.Arthur224.BankSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="users")
public class User {
    @Id
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String otherName;
    private String stateOfOrigin;
    private BigDecimal accountBalance;
    private String email;
    private String phoneNumber;
    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    @OneToMany(mappedBy = "sourceUser")
    List<Transaction> sourceTransactionList;
    @OneToMany(mappedBy = "destinationUser")
    List<Transaction> destinationTransactionList;


}
