package com.project.conforzone.services;

import com.project.conforzone.model.dto.*;
import jakarta.mail.MessagingException;

public interface AuthService {
    public AuthResponse login(LoginRequest loginRequest);
    public TokenModelDto register(RegisterRequest registerRequest) throws MessagingException;
    public void confirmRegister(String token) throws MessagingException;
}
