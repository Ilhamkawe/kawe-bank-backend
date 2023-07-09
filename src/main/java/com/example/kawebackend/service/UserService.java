package com.example.kawebackend.service;

import java.util.List;

import com.example.kawebackend.dto.reqbody.user.RegisterReqBody;
import com.example.kawebackend.dto.resbody.user.UserDTO;

public interface UserService {
    List<UserDTO> getUsersWithWallet();

    UserDTO registerUser(RegisterReqBody req);
}
