package com.example.kawebackend.util;

import com.example.kawebackend.dto.resbody.common.BaseResponse;
import com.example.kawebackend.dto.resbody.common.MetaResponse;
import com.example.kawebackend.dto.resbody.user.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ApiResponseUtil {
    public static ResponseEntity<BaseResponse<?>> ErrorHandler(Exception exception, HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(BaseResponse.builder()
                        .meta(new MetaResponse(message, status.value()))
                        .data(exception)
                        .build());
    }

    public static ResponseEntity<BaseResponse<?>> SuccessHandler(Object data, String message) {
        return ResponseEntity.ok(BaseResponse
                .builder()
                .meta(new MetaResponse("OK", HttpStatus.OK.value()))
                .data(data)
                .build());
    }
}
