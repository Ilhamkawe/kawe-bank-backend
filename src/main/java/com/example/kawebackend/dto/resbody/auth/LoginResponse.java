package com.example.kawebackend.dto.resbody.auth;

import com.example.kawebackend.dto.resbody.user.UserWalletDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String name;
    private String email;
    private String username;
    private UserWalletDTO wallet;
    private String token;
}
