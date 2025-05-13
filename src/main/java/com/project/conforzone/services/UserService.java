package com.project.conforzone.services;

import com.project.conforzone.model.dto.AuthResponse;
import com.project.conforzone.model.dto.UserModelDto;

public interface UserService {
    public UserModelDto getUserById(Integer id);
    public AuthResponse editUser(UserModelDto userModelDto);
    //public boolean deleteUserById(Integer id);
    public boolean existsByEmailIgnoreCase(String email);
}
