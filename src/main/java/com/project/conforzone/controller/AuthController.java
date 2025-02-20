package com.project.conforzone.controller;

import com.project.conforzone.auth.AuthResponse;
import com.project.conforzone.model.dto.LoginRequest;
import com.project.conforzone.model.dto.RegisterRequest;
import com.project.conforzone.model.dto.UserModelDto;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    public ResponseEntity<UserModelDto> register(RegisterRequest registerRequest);
    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest);
}
