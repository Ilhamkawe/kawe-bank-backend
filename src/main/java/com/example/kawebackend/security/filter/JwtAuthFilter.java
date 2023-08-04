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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // get header

        final String authHeader = request.getHeader("Authorization");

        // init jwttoken dan token

        String jwtToken = null;
        String email = null;

        // cek apakah token kosong, atau tidak mengandung keyword bearer

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            //v1
            jwtToken = authHeader.substring(7);
//            ekstrak name
            email = JwtAuthUtil.ExtractEmail(jwtToken);
        }

        // cek apakah username null dan security konteks null

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
           UserEntity user = userRepository.getUserByEmail(email);

            if(JwtAuthUtil.IsTokenValid(jwtToken, user)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))
                );

                authToken.setDetails(
                        new WebAuthenticationDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
