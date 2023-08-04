package com.example.kawebackend.controller;

import com.example.kawebackend.dto.reqbody.authentication.LoginReqBody;
import com.example.kawebackend.dto.reqbody.authentication.RegisterReqBody;
import com.example.kawebackend.dto.reqbody.user.UserReqBody;
import com.example.kawebackend.dto.resbody.common.BaseResponse;
import com.example.kawebackend.service.AuthService;
import com.example.kawebackend.service.UserService;
import com.example.kawebackend.util.ApiResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("api/v1/engine/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<BaseResponse<?>> register(@Validated @RequestBody RegisterReqBody req) {
        try {
            Object data = authService.registerUser(req);
            return ApiResponseUtil.SuccessHandler(data, "SUKSES");
        }catch(Exception e){
            return ApiResponseUtil.ErrorHandler(e, HttpStatus.NOT_FOUND, "GAGAL");
        }
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<BaseResponse<?>> login(@Validated @RequestBody LoginReqBody req){
        try {
            Object data = authService.loginUser(req);
            return ApiResponseUtil.SuccessHandler(data, "SUKSES");
        }catch(Exception e) {
            return ApiResponseUtil.ErrorHandler(e, HttpStatus.NOT_FOUND, "GAGAL");
        }
    }

//    @PutMapping("/{id}")
//    public @ResponseBody void update(@PathVariable int id) {
//        System.out.println("Test" + id);
//    }


}
