package com.Arthur224.BankSystem.service;

import com.Arthur224.BankSystem.dto.AccountInfo;
import com.Arthur224.BankSystem.dto.BankResponse;
import com.Arthur224.BankSystem.dto.EmailDetails;
import com.Arthur224.BankSystem.dto.UserRequest;
import com.Arthur224.BankSystem.entity.User;
import com.Arthur224.BankSystem.repository.UserRepository;
import com.Arthur224.BankSystem.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User newUser=User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.getAccountNumber())
                .email(userRequest.getEmail())
                .accountBalance(BigDecimal.ZERO)
                .phoneNumber(userRequest.getPhoneNumber())
                .status("ACTIVE")
                .build();
        User savedUser=userRepository.save(newUser);
        EmailDetails emailDetails=new EmailDetails().builder()
                .recipient(savedUser.getEmail())
                .subject("Account creation")
                .messageBody("Thanks for creating account on BankSystemApplication!\nGreat thanks, "+savedUser.getFirstName()+" "+savedUser.getOtherName()+".")
                .build();
        emailService.sendEmailAlert(emailDetails);
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(String.valueOf(savedUser.getAccountBalance()))
                        .accountNumber(savedUser.getAccountNumber())
                        .accountName(savedUser.getFirstName()+" "+savedUser.getLastName()+" "+savedUser.getOtherName())
                        .build())
                .build();
    }
}
