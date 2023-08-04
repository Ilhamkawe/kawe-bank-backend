package com.example.kawebackend.dto.reqbody.authentication;

import com.example.kawebackend.enumerate.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReqBody {
    private String name;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
    private String pin;
    private UserRole role;
    private int verified;
}
