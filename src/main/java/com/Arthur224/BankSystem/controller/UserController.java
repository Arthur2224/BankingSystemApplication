package com.Arthur224.BankSystem.controller;

import com.Arthur224.BankSystem.dto.*;
import com.Arthur224.BankSystem.service.TransactionService;
import com.Arthur224.BankSystem.service.UserService;
import com.Arthur224.BankSystem.dto.BankResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TransactionService transactionService;
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }
    @GetMapping("balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.balanceEnquiry(enquiryRequest);
    }
    @GetMapping("nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.nameEnquiry(enquiryRequest);
    }
    @GetMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest creditRequest){
        return  userService.creditAccount(creditRequest);
    }
    @GetMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest debitRequest){
        return  userService.debitAccount(debitRequest);
    }
    @GetMapping("transfer")
    public BankResponse debitAccount(@RequestBody TransferRequest transferRequest){
        return  userService.transfer(transferRequest);
    }

    @GetMapping("transactionData")
    public String dataEnquiry(@RequestBody EnquiryRequest transferRequest){
        return  transactionService.dataEnquiry(transferRequest);
    }

}
