package com.example.kawebackend.service;

import com.example.kawebackend.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUser();
}
