package com.Arthur224.BankSystem.utils;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.Random;

public class AccountUtils {
    public static final String ACCOUNT_EXIST_CODE="001";
    public static final String ACCOUNT_EXIST_MESSAGE="Email is already in use!";
    public static final String ACCOUNT_CREATION_SUCCESS="002";
    public static final String ACCOUNT_CREATION_MESSAGE="Account successfully created!";

    public static final String ACCOUNT_LOGIN_WRONG_PASSWORD="0021";
    public static final String ACCOUNT_LOGIN_WRONG_PASSWORD_MESSAGE="The wrong password!";
    public static final String ACCOUNT_SUCCESFULLY_LOGIN_CODE="0022";
    public static final String ACCOUNT_SUCCESFULLY_LOGIN_MESSAGE="Succesfully login!";
    public static final String ACCOUNT_NOT_EXIST_CODE="003";
    public static final String ACCOUNT_NOT_EXIST_MESSAGE="Account doesn't exist!";
    public static final String ACCOUNT_OF_DESTINATION_NOT_EXIST_CODE="0031";
    public static final String ACCOUNT_OF_DESTINATION_NOT_EXIST_MESSAGE="DESTINATION account doesn't exist!";
    public static final String ACCOUNT_OF_SOURCE_NOT_EXIST_CODE="0032";
    public static final String ACCOUNT_OF_SOURCE_NOT_EXIST_MESSAGE="SOURCE account doesn't exist!";
    public static final String ACCOUNT_FOUND_CODE="004";
    public static final String ACCOUNT_FOUND_MESSAGE="Account is successfuly founded!";
    public static final String ACCOUNT_CREDIT_CODE="005";
    public static final String ACCOUNT_CREDIT_MESSAGE="Account is successfuly credited!";
    public static final String ACCOUNT_DEBIT_CODE="006";
    public static final String ACCOUNT_DEBIT_MESSAGE="Account is successfuly debited!";
    public static final String ACCOUNT_NOT_AVAILABLE_TO_DEBIT_CODE="007";
    public static final String ACCOUNT_NOT_AVAILABLE_TO_DEBIT_MESSAGE="Account balance is not enough to debit!";
    public static final String ACCOUNT_TRANSFER_CODE="008";
    public static final String ACCOUNT_TRANSFER_MESSAGE="Transfer successfuly finished!";


    public static String getAccountNumber() {
        StringBuilder accountNumber=new StringBuilder();
        accountNumber.append(Year.now().toString()
                            +MonthDay.now().toString());
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}
