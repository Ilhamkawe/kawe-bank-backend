package com.example.kawebackend.controller;

import com.example.kawebackend.dto.resbody.common.BaseResponse;
import com.example.kawebackend.util.WalletUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.function.TriFunction;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/transfer")
    public @ResponseBody ResponseEntity<BaseResponse<?>> transfer(){
        return null;
    }
}
