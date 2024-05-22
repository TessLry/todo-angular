package com.leray.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leray.todoapi.dto.request.LoginRequest;
import com.leray.todoapi.dto.response.AuthResponse;
import com.leray.todoapi.service.AuthService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // @PostMapping("auth/register")
    // public ResponseEntity<AuthResponse> signup(SignUpRequest request) {
    //     return ResponseEntity.ok(authService.signup(request));
    // }
    
    
}
