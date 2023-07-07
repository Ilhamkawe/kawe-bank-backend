package com.example.kawebackend.controller;

import com.example.kawebackend.dto.UserDTO;
import com.example.kawebackend.dto.resbody.common.BaseResponse;
import com.example.kawebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/engine")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<BaseResponse<List<UserDTO>>>register(){
        return null;
    }
}
