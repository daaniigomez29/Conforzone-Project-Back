package com.project.conforzone.controller.impl;

import com.project.conforzone.auth.AuthResponse;
import com.project.conforzone.common.ApiEndpoints;
import com.project.conforzone.controller.AuthController;
import com.project.conforzone.model.dto.LoginRequest;
import com.project.conforzone.model.dto.RegisterRequest;
import com.project.conforzone.model.dto.UserModelDto;
import com.project.conforzone.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.BASE_AUTH)
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;
    @Override
    @PostMapping(ApiEndpoints.REGISTER_API)
    public ResponseEntity<UserModelDto> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @Override
    @PostMapping(ApiEndpoints.LOGIN_API)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
