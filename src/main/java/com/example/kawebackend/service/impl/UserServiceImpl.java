package com.example.kawebackend.service.impl;

import com.example.kawebackend.dto.UserDTO;
import com.example.kawebackend.entity.UserEntity;
import com.example.kawebackend.repository.UserRepository;
import com.example.kawebackend.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public List<UserDTO> getUser() {
        List<UserEntity> users = userRepository.getUsers();
        return objectMapper.convertValue(users, new TypeReference<List<UserDTO>>() {});

    }
}
