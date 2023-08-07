package com.example.kawebackend.dto.reqbody.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransferReqBody {
    private double amount;
    private String pin;
    private String sendTo;
 }
