package com.Arthur224.BankSystem.service;

import com.Arthur224.BankSystem.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
