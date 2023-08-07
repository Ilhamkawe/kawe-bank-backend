package com.example.kawebackend.util;

import com.example.kawebackend.entity.WalletEntity;
import com.example.kawebackend.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WalletUtil {
    @Autowired
    private WalletRepository walletRepository;

    public boolean pinCheck(int id, String pin){

        List<Object> wallet = walletRepository.getPin(id);
        String correctPin = (String) ((Object[])wallet.get(0))[1];
        return correctPin.equals(pin);

    }
}
