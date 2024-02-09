package com.Arthur224.BankSystem.controller;

import com.Arthur224.BankSystem.dto.BankResponse;
import com.Arthur224.BankSystem.dto.UserRequest;
import com.Arthur224.BankSystem.service.UserService;
import com.Arthur224.BankSystem.dto.BankResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }
}
