package com.example.kawebackend.service.impl;

import com.example.kawebackend.dto.ResMessageDTO;
import com.example.kawebackend.dto.reqbody.transfer.TransferReqBody;
import com.example.kawebackend.entity.TransactionEntity;
import com.example.kawebackend.entity.TransactionTypeEntity;
import com.example.kawebackend.entity.TransferHistoryEntity;
import com.example.kawebackend.entity.UserEntity;
import com.example.kawebackend.repository.*;
import com.example.kawebackend.service.TransferService;
import com.example.kawebackend.util.WalletUtil;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    @Autowired
    private WalletUtil walletUtil;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransferHistoryRepository transferHistoryRepository;

    @Override
    public Object transfer(TransferReqBody req) {
//        get data sender
        Object securityContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity authUser = (UserEntity) securityContext;
//        cek pin apakah pin valid ?
        boolean checkPin = walletUtil.pinCheck(authUser.getId(), req.getPin());
        if (!checkPin) {
            return new ResMessageDTO("Silahkan masukan PIN anda dengan benar");
        }
//        cek apakah mengirim ke rekening sendiri?
        if(authUser.getWallet().getCardNumber().equals(req.getSendTo())){
            return new ResMessageDTO("Tidak bisa transfer kerekening anda sendiri");
        }
//        apakah data receiver ada?
        UserEntity receiver = userRepository.getUserByCardNo(req.getSendTo());
        if(receiver == null){
            return new ResMessageDTO("Number Card tidak ditemukan");
        }
//        cek apakah saldo cukup
        if(authUser.getWallet().getBalance() < req.getAmount()){
            return new ResMessageDTO("Saldo anda tidak cukup untuk melakukan transaksi ini");
        }

        try {

            List<Object> type = transactionTypeRepository.getTypeTransfer();

            Object receiveTransactionType = type.get(1);
            Object transferTransactionType = type.get(0);

            TransactionEntity transfer = new TransactionEntity();
            transfer.setUserId(authUser.getId());
            transfer.setTransactionTypeId(((TransactionTypeEntity)transferTransactionType).getId());
            transfer.setDescription("Mengirim dana ke " + receiver.getEmail());
            transfer.setAmount(req.getAmount());

            transactionRepository.save(transfer);

            walletRepository.decrementBalance( authUser.getWallet().getId(),req.getAmount());

            TransactionEntity receive = new TransactionEntity();
            receive.setUserId(receiver.getId());
            receive.setTransactionTypeId(((TransactionTypeEntity)receiveTransactionType).getId());
            receive.setDescription("Menerima dana Dari " + authUser.getEmail());

            transactionRepository.save(receive);

            walletRepository.incrementBalance(receiver.getId(),req.getAmount());

            TransferHistoryEntity trfHistory = new TransferHistoryEntity();
            trfHistory.setSenderId(authUser.getId());
            trfHistory.setReceiverId(receive.getId());
            trfHistory.setTransactionCode(RandomStringUtils.random(10, true, true));
            transferHistoryRepository.save(trfHistory);

            return new ResMessageDTO("Transfer Sukses");

        }catch (Exception e){
            return e;
        }

    }
}
