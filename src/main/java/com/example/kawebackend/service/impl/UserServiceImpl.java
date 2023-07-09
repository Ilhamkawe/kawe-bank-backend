package com.example.kawebackend.service.impl;

import com.example.kawebackend.dto.reqbody.user.RegisterReqBody;
import com.example.kawebackend.dto.ErrorMessageDTO;
import com.example.kawebackend.dto.resbody.user.UserDTO;
import com.example.kawebackend.entity.UserEntity;
import com.example.kawebackend.repository.UserRepository;
import com.example.kawebackend.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class xxx implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<UserDTO> getUsersWithWallet() {
        List<UserEntity> users = userRepository.getUsersWithWallet();
        return objectMapper.convertValue(usesasrs, ne
                 TypeReference<List<UserDTO>>() {
        });
aa
    }

    public Object registerUser(RegisterReqBody req) {

        //cek email
        int isEmailExist = userRepository.isEmailExist();
        if(isEmailExist > 0){
            return new ErrorMessageDTO("Email Sudah Digunakan");
        }


    }
}
