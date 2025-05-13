package com.project.conforzone.services.impl;

import com.project.conforzone.model.dto.AuthResponse;
import com.project.conforzone.security.JwtService;
import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.UserModel;
import com.project.conforzone.model.dto.UserModelDto;
import com.project.conforzone.repository.UserRepository;
import com.project.conforzone.services.UserService;
import com.project.conforzone.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapper modelMapper;
    private final JwtService jwtService;
    @Override
    public UserModelDto getUserById(Integer id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        return userModel.map(modelMapper::toUserDTO).orElse(null);
    }

    @Override
    public AuthResponse editUser(UserModelDto userModelDto) {
        UserModel userEdit = userRepository.findById(userModelDto.getId()).orElseThrow(() -> new GlobalException("El usuario a editar no existe"));
        userEdit.setName(userModelDto.getName());
        userEdit.setTlf(userModelDto.getTlf());
        userEdit.setAddress(userModelDto.getAddress());
        userEdit.setLastName(userModelDto.getLastName());

        Map<String, Object> extraClaims = new HashMap<>(); //Declara toda la información que quiere que tenga el token además del correo
        extraClaims.put("idUser", userEdit.getId());
        extraClaims.put("name", userEdit.getName());
        extraClaims.put("role", userEdit.getRole());
        String token = jwtService.getTokenFromService(extraClaims, userEdit);
        userRepository.save(userEdit);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public boolean existsByEmailIgnoreCase(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }
}
