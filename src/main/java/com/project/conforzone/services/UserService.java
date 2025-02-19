package com.project.conforzone.services;

import com.project.conforzone.auth.AuthResponse;
import com.project.conforzone.model.dto.PurchaseBookingModelDto;
import com.project.conforzone.model.dto.UserModelDto;

import java.util.List;

public interface UserService {
    public UserModelDto getUserById(Integer id);
    public AuthResponse editUser(UserModelDto userModelDto);
    //public boolean deleteUserById(Integer id);
    public boolean existsByEmailIgnoreCase(String email);
}
