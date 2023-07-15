package com.example.kawebackend.service;

import java.util.List;

import com.example.kawebackend.dto.reqbody.user.RegisterReqBody;
import com.example.kawebackend.dto.reqbody.user.UserReqBody;
import com.example.kawebackend.dto.resbody.user.UserDTO;

public interface UserService {
    List<UserDTO> getUsersWithWallet();
    UserDTO getUsersWithWalletById(int id);
    Object registerUser(RegisterReqBody req);
    Object updateUser(int id, UserReqBody req);
}
