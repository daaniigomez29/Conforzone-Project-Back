package com.project.conforzone.services;

import com.project.conforzone.auth.AuthResponse;
import com.project.conforzone.model.dto.LoginRequest;
import com.project.conforzone.model.dto.RegisterRequest;
import com.project.conforzone.model.dto.UserModelDto;

public interface AuthService {
    public AuthResponse login(LoginRequest loginRequest);
    public UserModelDto register(RegisterRequest registerRequest);
}
