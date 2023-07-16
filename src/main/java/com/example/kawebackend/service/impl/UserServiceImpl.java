package com.example.kawebackend.service.impl;

import com.example.kawebackend.dto.ErrorMessageDTO;
import com.example.kawebackend.dto.reqbody.authentication.LoginReqBody;
import com.example.kawebackend.dto.reqbody.authentication.RegisterReqBody;
import com.example.kawebackend.dto.reqbody.user.UserReqBody;
import com.example.kawebackend.dto.resbody.user.UserDTO;
import com.example.kawebackend.entity.UserEntity;
import com.example.kawebackend.entity.WalletEntity;
import com.example.kawebackend.repository.UserRepository;
import com.example.kawebackend.repository.WalletRepository;
import com.example.kawebackend.service.UserService;
import com.example.kawebackend.util.CardUtil;
import com.example.kawebackend.util.ImageBase64Util;
import com.example.kawebackend.util.PasswordEncoderUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

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
    public Object registerUser(RegisterReqBody req) {
        //cek email
        req.setVerified(0);
        Boolean isEmailExist = userRepository.isEmailExist(req.getEmail());
        if (isEmailExist) {
            return new ErrorMessageDTO("Email Sudah Digunakan");
        }

        req.setPassword(PasswordEncoderUtil.EncodePassword(req.getPassword()));

        Boolean isUsernameExist = userRepository.isUsernameExist(req.getUsername());
        if (isUsernameExist) {
            return new ErrorMessageDTO("Username Sudah Digunakan");
        }

        WalletEntity walletData = new WalletEntity();

        try {
            UserEntity user = objectMapper.convertValue(req, new TypeReference<UserEntity>() {});
//            upload image via base64
            if(!req.getProfilePicture().equals("")){
                byte[] imageByte = Base64.decodeBase64(user.getProfilePicture());
                String extension = ImageBase64Util.getFileExtension(req.getProfilePicture());

                if(extension.equals("")){
                    return new ErrorMessageDTO("Ekstensi gambar belum didukung");
                }

                String filename = UUID.randomUUID() + extension;
//                upload image logic v1
//                String path = servletContext.getRealPath("/")+"images/"+  filename;
//
//                FileOutputStream fileOutputStream = new FileOutputStream(path);
//                fileOutputStream.write(imageByte);
//                fileOutputStream.close();

//                upload image logic v2
                String root = System.getProperty("catalina.home");
                File saveDir = new File(root + File.separator + "image");

                if (!saveDir.exists()){
                    saveDir.mkdirs();
                }

                File scanFile = new File(saveDir.getAbsolutePath() + File.separator + filename);
                BufferedOutputStream scanStream = new BufferedOutputStream(new FileOutputStream(scanFile));

                scanStream.write(imageByte);
                scanStream.close();

                user.setProfilePicture(filename);

            }

            UserEntity savedUser = userRepository.save(user);

            walletData.setPin(req.getPin());
            walletData.setBalance(0);
            walletData.setCardNumber(CardUtil.generateCardNumber());
            walletData.setUserId(savedUser.getId());

            walletRepository.save(walletData);

            UserEntity resData = userRepository.getUsersWithWalletById(savedUser.getId());
            return objectMapper.convertValue(resData, new TypeReference<UserDTO>() {
            });
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object updateUser(int id, UserReqBody req){
        UserEntity user = userRepository.getUserById(id);

        if (user == null) {
            return new ErrorMessageDTO("User Tidak Ditemukan");
        }

        if(!user.getEmail().equals(req.getEmail())){
            Boolean isEmailExist = userRepository.isEmailExist(req.getEmail());
            if (isEmailExist) {
                return new ErrorMessageDTO("Email Sudah Digunakan");
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
    public Object loginUser(LoginReqBody req) {
        UserEntity user = userRepository.getUserByEmail(req.getEmail());

        if (user == null) {
            return new ErrorMessageDTO("User Tidak Ditemukan");
        }



        return null;
    }


}
