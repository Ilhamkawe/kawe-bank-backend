package com.example.kawebackend.dto;

import com.example.kawebackend.entity.WalletEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    int id;
    String name;
    String email;
    String username;
    String verified;
    WalletEntity wallet;
    String profilePicture;
}
