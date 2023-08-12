package com.example.kawebackend.controller;

import com.example.kawebackend.dto.reqbody.transfer.TransferReqBody;
import com.example.kawebackend.dto.reqbody.wallet.UpdatePinReqBody;
import com.example.kawebackend.dto.resbody.common.BaseResponse;
import com.example.kawebackend.dto.resbody.user.UserDTO;
import com.example.kawebackend.service.TransferService;
import com.example.kawebackend.util.ApiResponseUtil;
import com.example.kawebackend.util.WalletUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.function.TriFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("api/v1/engine/transaction")
public class TransferController {
    @Autowired
    WalletUtil walletUtil;

    @Autowired
    TransferService transferService;
    @PostMapping("/transfer")
    public @ResponseBody ResponseEntity<BaseResponse<?>> transfer(@RequestBody TransferReqBody req){
        try {
            Object users = transferService.transfer(req);
            return ApiResponseUtil.SuccessHandler(users, "SUKSES");
        }catch (Exception e){
            return ApiResponseUtil.ErrorHandler(e, HttpStatus.NOT_FOUND, "GAGAL");
        }
    }
}
