package com.example.kawebackend.service.impl;

import com.example.kawebackend.dto.ResMessageDTO;
import com.example.kawebackend.dto.reqbody.wallet.UpdatePinReqBody;
import com.example.kawebackend.dto.resbody.wallet.WalletDTO;
import com.example.kawebackend.entity.UserEntity;
import com.example.kawebackend.entity.WalletEntity;
import com.example.kawebackend.repository.UserRepository;
import com.example.kawebackend.repository.WalletRepository;
import com.example.kawebackend.service.WalletService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Object updatePin(UpdatePinReqBody req) {

        Object securityContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserEntity authUser = (UserEntity) securityContext;

        WalletEntity wallet = walletRepository.getById(authUser.getWallet().getId());

        // jika pin lama yang diinput tidak sama dengan pin di db
        if(!req.getPreviousPin().equals(wallet.getPin())){
            return new ResMessageDTO("PIN lama tidak cocok");
        }

        // jika pin baru sama denegan pin didb (tidak ada perubahan)
        if(req.getNewPin().equals(wallet.getPin())){
            return new ResMessageDTO("PIN baru tidak boleh sama dengan PIN lama");
        }

        if(authUser.getId() != wallet.getId()){
            return new ResMessageDTO("wallet ini bukan milik user");
        }

        try {
            wallet.setPin(req.getNewPin());
            return walletRepository.save(wallet);
        }catch (Exception e){
            return e;
        }
    }

    @Override
    public Object getWallet() {
        Object securityContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserEntity authUser = (UserEntity) securityContext;

        try {
           WalletEntity wallet = walletRepository.getById(authUser.getWallet().getId());
           return objectMapper.convertValue(wallet, new TypeReference<WalletDTO>() {
           });
        }catch (Exception e){
            return e;
        }
    }
}
