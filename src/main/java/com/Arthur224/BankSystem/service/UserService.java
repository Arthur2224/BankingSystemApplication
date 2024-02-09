package com.Arthur224.BankSystem.service;

import com.Arthur224.BankSystem.dto.BankResponse;
import com.Arthur224.BankSystem.dto.UserRequest;
import com.Arthur224.BankSystem.dto.BankResponse;
import com.Arthur224.BankSystem.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
}
