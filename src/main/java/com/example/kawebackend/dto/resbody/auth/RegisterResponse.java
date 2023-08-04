package com.example.kawebackend.dto.resbody.auth;

import com.example.kawebackend.dto.resbody.user.UserWalletDTO;
import com.example.kawebackend.enumerate.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    int id;
    String name;
    String email;
    String username;
    int verified;
    UserWalletDTO wallet;
    String profilePicture;
    UserRole role;
    String token;
}
