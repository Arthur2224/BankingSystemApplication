package com.Arthur224.BankSystem.service;

import com.Arthur224.BankSystem.dto.*;
import com.Arthur224.BankSystem.entity.Transaction;
import com.Arthur224.BankSystem.entity.User;
import com.Arthur224.BankSystem.repository.TransactionRepository;
import com.Arthur224.BankSystem.repository.UserRepository;
import com.Arthur224.BankSystem.utils.AccountUtils;
import com.Arthur224.BankSystem.utils.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            System.out.println("Exist");
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
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
        User savedUser=userRepository.save(newUser);
    /*
        EmailDetails emailDetails=new EmailDetails().builder()
                .recipient(savedUser.getEmail())
                .subject("Account creation")
                .messageBody("Thanks for creating account on BankSystemApplication!\nGreat thanks, "+savedUser.getFirstName()+" "+savedUser.getOtherName()+".")
                .build();

        emailService.sendEmailAlert(emailDetails);
*/


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


    @Override
    public BankResponse balanceEnquiry(EnquiryRequest enquiryRequest) {
        boolean isAccountExist=userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber());
        if(!isAccountExist){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User foundUser=userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessage(AccountUtils.ACCOUNT_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(String.valueOf(foundUser.getAccountBalance()))
                        .accountNumber(foundUser.getAccountNumber())
                        .accountName(foundUser.getFirstName()+" "+foundUser.getLastName()+" "+foundUser.getOtherName())
                        .build())
                .build();
    }

    @Override
    public String nameEnquiry(EnquiryRequest request) {
        boolean isAccountExist=userRepository.existsByAccountNumber(request.getAccountNumber());
        if(!isAccountExist){
            return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;
        }
        User foundUser=userRepository.findByAccountNumber(request.getAccountNumber());
        return foundUser.getFirstName() + " "+ foundUser.getLastName();

    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest creditRequest) {

        boolean isAccountExist=userRepository.existsByAccountNumber(creditRequest.getAccountNumber());
        if(!isAccountExist){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User userToCredit= userRepository.findByAccountNumber(creditRequest.getAccountNumber());
        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(creditRequest.getAmount()));
        userRepository.save(userToCredit);
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREDIT_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREDIT_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(String.valueOf(userToCredit.getAccountBalance()))
                        .accountNumber(userToCredit.getAccountNumber())
                        .accountName(userToCredit.getFirstName()+" "+userToCredit.getLastName()+" "+userToCredit.getOtherName()+" is credited by ammount: "+ creditRequest.getAmount())
                        .build())
                .build();
    }

    @Override
    public BankResponse debitAccount(CreditDebitRequest debitRequest) {
        boolean isAccountExist=userRepository.existsByAccountNumber(debitRequest.getAccountNumber());
        if(!isAccountExist){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User userToDebit= userRepository.findByAccountNumber(debitRequest.getAccountNumber());
        if(userToDebit.getAccountBalance().compareTo(debitRequest.getAmount())>=0){
        userToDebit.setAccountBalance(userToDebit.getAccountBalance().subtract(debitRequest.getAmount()));
        userRepository.save(userToDebit );
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_DEBIT_CODE)
                .responseMessage(AccountUtils.ACCOUNT_DEBIT_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(String.valueOf(userToDebit.getAccountBalance()))
                        .accountNumber(userToDebit.getAccountNumber())
                        .accountName(userToDebit.getFirstName()+" "+userToDebit.getLastName()+" "+userToDebit.getOtherName()+" is debited by ammount: "+ debitRequest.getAmount())
                        .build())
                .build();}

    return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_NOT_AVAILABLE_TO_DEBIT_CODE)
                .responseMessage(AccountUtils.ACCOUNT_NOT_AVAILABLE_TO_DEBIT_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(String.valueOf(userToDebit.getAccountBalance()))
                        .accountNumber(userToDebit.getAccountNumber())
                        .accountName(userToDebit.getFirstName()+" "+userToDebit.getLastName()+" "+userToDebit.getOtherName()+" is not able to debit by ammount: "+ debitRequest.getAmount())
                        .build())
                .build();
    }

    @Override
    public BankResponse transfer(TransferRequest transferRequest) {
        /* 1. Check if exist source and destination accounts
            2. Check if source acc balance is enough to debit money
            3. If yes than debit from source and credit to destination account
          */

        boolean isDestinationAccNumberExists= userRepository.existsByAccountNumber(transferRequest.getDestinationAccNumber());
        boolean isSourceAccNumberExists=userRepository.existsByAccountNumber(transferRequest.getSourceAccNumber());


        if(!isDestinationAccNumberExists){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_OF_DESTINATION_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_OF_DESTINATION_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        if(!isSourceAccNumberExists){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_OF_SOURCE_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_OF_SOURCE_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User sourceUser=userRepository.findByAccountNumber(transferRequest.getSourceAccNumber());
        User destinationUser=userRepository.findByAccountNumber(transferRequest.getDestinationAccNumber());
        BigDecimal accountBalanceOfSourceUser = sourceUser.getAccountBalance();


        if(Double.compare(transferRequest.getAmount(),accountBalanceOfSourceUser.doubleValue())==-1){
            destinationUser.setAccountBalance(destinationUser.getAccountBalance().add(BigDecimal.valueOf(transferRequest.getAmount())));
            TransactionDetails transactionDetails=TransactionDetails.builder()
                    .transactionId(TransactionUtils.getTransactionId())
                    .transactionType("TRANSFER")
                    .idOfDestinationAccount(destinationUser)
                    .idOfSourceAccount(sourceUser)
                    .amount(BigDecimal.valueOf(transferRequest.getAmount()))
                    .build();
            Transaction transaction=transactionRepository.findByTransactionId(transactionDetails.getTransactionId());


            sourceUser.getSourceTransactionList().add(transaction);
            destinationUser.getDestinationTransactionList().add(transaction);


            userRepository.save(sourceUser);
            userRepository.save(destinationUser);
            transactionService.saveTransaction(transactionDetails);
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_TRANSFER_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_TRANSFER_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountBalance(String.valueOf(sourceUser.getAccountBalance()))
                            .accountNumber(sourceUser.getAccountNumber())
                            .accountName(sourceUser.getFirstName()+" "+sourceUser.getLastName()+" "+sourceUser.getOtherName()+" successfuly transfered money in amount of : "+ transferRequest.getAmount()+" to "+destinationUser.getFirstName()+" "+destinationUser.getLastName()+" "+destinationUser.getOtherName())
                            .build())
                    .build();

           }

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_NOT_AVAILABLE_TO_DEBIT_CODE)
                .responseMessage(AccountUtils.ACCOUNT_NOT_AVAILABLE_TO_DEBIT_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(String.valueOf(sourceUser.getAccountBalance()))
                        .accountNumber(sourceUser.getAccountNumber())
                        .accountName(sourceUser.getFirstName()+" "+sourceUser.getLastName()+" "+sourceUser.getOtherName()+" is not able to debit money in  ammount of : "+ transferRequest.getAmount())
                        .build())
                .build();
    }
    @Override
    public BankResponse loginIntoAccount(UserRequest userRequest)
    {        
        if( !userRepository.existsByEmail(userRequest.getEmail()))
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
       else {
            User sourceUser=userRepository.findByEmail(userRequest.getEmail());
            if(passwordEncoder.matches(userRequest.getPassword(),sourceUser.getPassword())) {
                return BankResponse.builder()
                        .responseCode(AccountUtils.ACCOUNT_SUCCESFULLY_LOGIN_CODE)
                        .responseMessage(AccountUtils.ACCOUNT_SUCCESFULLY_LOGIN_MESSAGE)
                        .accountInfo(AccountInfo.builder()
                                .accountBalance(String.valueOf(sourceUser.getAccountBalance()))
                                .accountNumber(sourceUser.getAccountNumber())
                                .accountName(sourceUser.getFirstName() + " " + sourceUser.getLastName() + " " + sourceUser.getOtherName())
                                .build())
                        .build();
            }
            else{
                return BankResponse.builder()
                        .responseCode(AccountUtils.ACCOUNT_LOGIN_WRONG_PASSWORD)
                        .responseMessage(AccountUtils.ACCOUNT_LOGIN_WRONG_PASSWORD_MESSAGE)
                        .accountInfo(null)
                        .build();
                }
       }

       }
    }




