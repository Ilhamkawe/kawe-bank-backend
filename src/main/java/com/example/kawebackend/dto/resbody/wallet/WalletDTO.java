package com.example.kawebackend.dto.resbody.wallet;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WalletDTO {
    private int id;
    private double balance;
    private String pin;
    private String cardNumber;
    private int userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
