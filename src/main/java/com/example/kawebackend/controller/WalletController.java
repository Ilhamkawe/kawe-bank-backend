package com.example.kawebackend.controller;

import com.example.kawebackend.dto.reqbody.wallet.UpdatePinReqBody;
import com.example.kawebackend.dto.resbody.common.BaseResponse;
import com.example.kawebackend.service.WalletService;
import com.example.kawebackend.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/engine/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PutMapping("/update")
    public @ResponseBody ResponseEntity<BaseResponse<?>> updatePin(@RequestBody UpdatePinReqBody req){
        try {
            return ApiResponseUtil.SuccessHandler(walletService.updatePin(req),"SUKSES");
        } catch (Exception e) {
            return ApiResponseUtil.ErrorHandler(e, HttpStatus.NOT_FOUND, "GAGAL");
        }
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<BaseResponse<?>> getWallet() {
        try {
            return ApiResponseUtil.SuccessHandler(walletService.getWallet(), "SUKSES");
        }catch (Exception e){
            return ApiResponseUtil.ErrorHandler(e, HttpStatus.NOT_FOUND, "GAGAL");
        }
    }
}
