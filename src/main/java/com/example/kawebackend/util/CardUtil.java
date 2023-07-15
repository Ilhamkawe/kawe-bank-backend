package com.example.kawebackend.util;

import java.util.Random;

public class CardUtil {

    public static String generateCardNumber(){


        String cardNumber = generateRandomString(4) + generateRandomString(4) + generateRandomString(4) +generateRandomString(4);

        return cardNumber;
    }

    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomNum = random.nextInt(10);
            stringBuilder.append(randomNum);
        }

        return stringBuilder.toString();
    }

}
