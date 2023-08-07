package com.example.kawebackend.service.impl;

import com.example.kawebackend.dto.ErrorMessageDTO;
import com.example.kawebackend.dto.reqbody.transfer.TransferReqBody;
import com.example.kawebackend.entity.UserEntity;
import com.example.kawebackend.repository.UserRepository;
import com.example.kawebackend.repository.WalletRepository;
import com.example.kawebackend.service.TransferService;
import com.example.kawebackend.util.WalletUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    @Autowired
    private WalletUtil walletUtil;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Object transfer(TransferReqBody req) {
//        get data sender
        Object securityContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity authUser = (UserEntity) securityContext;
//        cek pin apakah pin valid ?
        boolean checkPin = walletUtil.pinCheck(authUser.getId(), req.getPin());
        if (!checkPin) {
            return new ErrorMessageDTO("Silahkan masukan PIN anda dengan benar");
        }
//        cek apakah mengirim ke rekening sendiri?
        if(authUser.getWallet().getCardNumber().equals(req.getSendTo())){
            return new ErrorMessageDTO("Tidak bisa transfer kerekening anda sendiri");
        }
//        apakah data receiver ada?
        UserEntity receiver = userRepository.getUserByCardNo(req.getSendTo());
        if(receiver == null){
            return new ErrorMessageDTO("Number Card tidak ditemukan");
        }
//        cek apakah saldo cukup
        if(authUser.getWallet().getBalance() < req.getAmount()){
            return new ErrorMessageDTO("Saldo anda tidak cukup untuk melakukan transaksi ini");
        }



        return null;

    }
}
