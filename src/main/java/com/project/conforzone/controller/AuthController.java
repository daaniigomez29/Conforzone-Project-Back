package com.project.conforzone.controller;

import com.project.conforzone.model.dto.*;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    public ResponseEntity<TokenModelDto> register(RegisterRequest registerRequest) throws MessagingException;
    public void confirmRegister(String token) throws MessagingException;

    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest);
}
