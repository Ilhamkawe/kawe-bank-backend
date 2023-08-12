package com.example.kawebackend.service.impl;

import com.example.kawebackend.dto.ResMessageDTO;
import com.example.kawebackend.dto.reqbody.user.UserReqBody;
import com.example.kawebackend.dto.resbody.user.UserDTO;
import com.example.kawebackend.entity.UserEntity;
import com.example.kawebackend.repository.UserRepository;
import com.example.kawebackend.repository.WalletRepository;
import com.example.kawebackend.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<UserDTO> getUsersWithWallet() {
        List<UserEntity> users = userRepository.getUsersWithWallet();
        return objectMapper.convertValue(users, new TypeReference<List<UserDTO>>() {
        });
    }

    @Override
    public UserDTO getUsersWithWalletById(int id) {
        UserEntity user = userRepository.getUsersWithWalletById(id);
        return objectMapper.convertValue(user, new TypeReference<UserDTO>() {
        });
    }

    @Override
    public Object updateUser(int id, UserReqBody req){
        UserEntity user = userRepository.getUserById(id);

        if (user == null) {
            return new ResMessageDTO("User Tidak Ditemukan");
        }

        if(!user.getEmail().equals(req.getEmail())){
            Boolean isEmailExist = userRepository.isEmailExist(req.getEmail());
            if (isEmailExist) {
                return new ResMessageDTO("Email Sudah Digunakan");
            }
        }

        try {
            user.setName(req.getName());
            user.setEmail(req.getEmail());

            UserEntity resData = userRepository.save(user);

            return objectMapper.convertValue(resData, new TypeReference<UserDTO>() {});
        }catch (Exception e){
            return e;
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return (UserDetails) userRepository.findByEmail(username)
                        .orElseThrow(()->new UsernameNotFoundException("User tidak ditemukan"));
            }
        };
    }


}
