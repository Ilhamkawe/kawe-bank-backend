package com.example.kawebackend.dto.resbody.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWalletDTO {
    private int balance;
    private String ping;
    private String cardNumber;
}
