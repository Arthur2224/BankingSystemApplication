package com.Arthur224.BankSystem.utils;

import java.time.*;
import java.util.Random;

public class TransactionUtils {
    public static String getTransactionId(){
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        StringBuilder transactionID=new StringBuilder();
        transactionID.append(Year.now().toString()
                            + Month.of(currentDate.getMonthValue()));
         Random random = new Random();
        for (int i = 0; i < 10; i++) {
            transactionID.append(random.nextInt(10));
        }
        transactionID.append(MonthDay.now().toString()
                            +currentTime.getHour()+"H"
                            +currentTime.getMinute()+"MINUTES"
                            +currentTime.getSecond()+"SECONDS");
        return transactionID.toString();
}
}
