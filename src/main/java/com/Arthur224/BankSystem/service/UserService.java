package com.Arthur224.BankSystem.service;

import com.Arthur224.BankSystem.dto.*;
import com.Arthur224.BankSystem.dto.BankResponse;
import com.Arthur224.BankSystem.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);
    String nameEnquiry(EnquiryRequest request);
    BankResponse creditAccount(CreditDebitRequest creditRequest);
    BankResponse debitAccount(CreditDebitRequest debitRequest);
    BankResponse transfer(TransferRequest transferRequest);
    BankResponse loginIntoAccount(UserRequest userRequest);



}
