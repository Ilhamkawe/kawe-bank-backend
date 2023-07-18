package com.example.kawebackend.security.filter;

import com.example.kawebackend.entity.UserEntity;
import com.example.kawebackend.repository.UserRepository;
import com.example.kawebackend.service.UserService;
import com.example.kawebackend.util.JwtAuthUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private final JwtAuthUtil jwtAuthUtil;
    @Autowired
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // get header

        final String authHeader = request.getHeader("Authorization");

        // init jwttoken dan token

        String jwtToken = null;
        String email = null;

        // cek apakah token kosong, atau tidak mengandung keyword bearer

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);

            // ekstrak name
            email = JwtAuthUtil.ExtractEmail(jwtToken);

        }

        // cek apakah username null dan security konteks null

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserEntity user = this.userRepository.getUserByEmail(email);
        }
    }
}
