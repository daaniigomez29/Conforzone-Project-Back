package com.project.conforzone.controller.impl;

import com.project.conforzone.auth.AuthResponse;
import com.project.conforzone.common.ApiEndpoints;
import com.project.conforzone.controller.UserController;
import com.project.conforzone.model.dto.UserModelDto;
import com.project.conforzone.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.BASE_USER)
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    @Override
    @GetMapping()
    public ResponseEntity<UserModelDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    @PutMapping(ApiEndpoints.BASE_EDIT + ApiEndpoints.BASE_ID)
    public ResponseEntity<AuthResponse> editUser(@RequestBody UserModelDto userModelDto) {
        return ResponseEntity.ok(userService.editUser(userModelDto));
    }

    @Override
    @GetMapping(ApiEndpoints.EXISTS_EMAIL)
    public boolean existsByEmailIgnoreCase(@PathVariable String email) {
        return userService.existsByEmailIgnoreCase(email);
    }
}
