package com.project.conforzone.controller;

import com.project.conforzone.auth.AuthResponse;
import com.project.conforzone.model.dto.UserModelDto;
import org.springframework.http.ResponseEntity;

public interface UserController {
    public ResponseEntity<UserModelDto> getUserById(Integer id);
    public ResponseEntity<AuthResponse> editUser(UserModelDto userModelDto);
    //public boolean deleteUserById(Integer id);
    public boolean existsByEmailIgnoreCase(String email);
}
