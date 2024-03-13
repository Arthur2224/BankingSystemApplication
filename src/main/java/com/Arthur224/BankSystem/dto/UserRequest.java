package com.Arthur224.BankSystem.dto;

import lombok.*;

import java.math.BigDecimal;
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String otherName;
    private String stateOfOrigin;
    private String email;
    private String phoneNumber;
    private String password;


}
