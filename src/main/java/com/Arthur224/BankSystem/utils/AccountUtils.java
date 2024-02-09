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
