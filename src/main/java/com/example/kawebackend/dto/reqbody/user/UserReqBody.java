package com.example.kawebackend.dto.reqbody.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReqBody {
    private int id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String verified;
    private String profilePicture;
    private String ktp;

}
