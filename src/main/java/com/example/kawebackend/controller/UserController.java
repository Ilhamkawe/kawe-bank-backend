package com.example.kawebackend.controller;

import com.example.kawebackend.dto.UserDTO;
import com.example.kawebackend.dto.resbody.common.BaseResponse;
import com.example.kawebackend.dto.resbody.common.MetaResponse;
import com.example.kawebackend.service.UserService;
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
    public @ResponseBody ResponseEntity<BaseResponse<List<UserDTO>>> getUser(){
        return ResponseEntity.ok(BaseResponse
                .<List<UserDTO>>builder()
                .meta(new MetaResponse("OK", HttpStatus.OK.value()))
                .data(userService.getUser())
                .build());
    }

}