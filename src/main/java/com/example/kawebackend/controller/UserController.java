package com.example.kawebackend.controller;

import com.example.kawebackend.dto.reqbody.user.UserReqBody;
import com.example.kawebackend.dto.resbody.common.BaseResponse;
import com.example.kawebackend.dto.resbody.common.MetaResponse;
import com.example.kawebackend.dto.resbody.user.UserDTO;
import com.example.kawebackend.service.UserService;
import com.example.kawebackend.util.ApiResponseUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/engine")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public @ResponseBody ResponseEntity<BaseResponse<?>> getUsers() {
        try {
            List<UserDTO> users = userService.getUsersWithWallet();
            return ApiResponseUtil.SuccessHandler(users, "SUKSES");
        }catch (Exception e){
            return ApiResponseUtil.ErrorHandler(e, HttpStatus.NOT_FOUND, "GAGAL");
        }
    }

    @GetMapping("/user/{id}")
    public @ResponseBody ResponseEntity<BaseResponse<?>> getUser(@PathVariable int id) {
        try {
            UserDTO users = userService.getUsersWithWalletById(id);
            return ApiResponseUtil.SuccessHandler(users, "SUKSES");
        }catch (Exception e){
            return ApiResponseUtil.ErrorHandler(e, HttpStatus.NOT_FOUND, "GAGAL");
        }
    }

//    @PutMapping("/user/{id}")
//    public @ResponseBody ResponseEntity<BaseResponse<?>> updateUser(@PathVariable int id, @RequestBody UserReqBody req){
//        try {
//
//        }catch(Exception e){
//            return ApiResponseUtil.ErrorHandler(e, HttpStatus.NOT_FOUND, "GAGAL");
//        }
//    }

}