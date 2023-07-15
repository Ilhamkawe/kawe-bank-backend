package com.example.kawebackend.dto.resbody.user;

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
    int verified;
    UserWalletDTO wallet;
    String profilePicture;
}
