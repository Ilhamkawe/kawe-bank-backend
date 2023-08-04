package com.example.kawebackend.service;

import com.example.kawebackend.dto.reqbody.wallet.UpdatePinReqBody;
import com.example.kawebackend.entity.WalletEntity;

public interface WalletService {

    Object updatePin(UpdatePinReqBody req);
    Object getWallet();

}
