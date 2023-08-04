package com.example.kawebackend.service;

import com.example.kawebackend.dto.reqbody.authentication.LoginReqBody;
import com.example.kawebackend.dto.reqbody.authentication.RegisterReqBody;

public interface AuthService {
    Object registerUser(RegisterReqBody req);
    Object loginUser(LoginReqBody req);
}
