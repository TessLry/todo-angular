package com.leray.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.leray.todoapi.dto.request.LoginRequest;
import com.leray.todoapi.dto.response.AuthResponse;
import com.leray.todoapi.entity.User;
import com.leray.todoapi.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
 
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String jwt = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwt).build();
    }

    // public AuthResponse signup(SignUpRequest request) {
    //     User user = User.builder()
    //             .email(request.getEmail())
    //             .password(passwordEncoder.encode(request.getPassword()))
    //             .build();
    //     userRepository.save(user);
    //     String jwt = jwtService.generateToken(user);
    //     return AuthResponse.builder().token(jwt).build();
    // }
    
}
