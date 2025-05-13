package com.project.conforzone.controller.impl;

import com.project.conforzone.model.dto.*;
import com.project.conforzone.common.ApiEndpoints;
import com.project.conforzone.controller.AuthController;
import com.project.conforzone.services.AuthService;
import jakarta.mail.MessagingException;
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
    public ResponseEntity<TokenModelDto> register(@RequestBody RegisterRequest registerRequest) throws MessagingException {
        return null;
    }

    @Override
    @GetMapping(ApiEndpoints.CONFIRM_REGISTER_API)
    public String confirmRegister(@PathVariable String token) throws MessagingException {
        return null;
    }

    @Override
    @PostMapping(ApiEndpoints.LOGIN_API)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return null;
    }
}
