package com.project.conforzone.services.impl;

import com.project.conforzone.auth.AuthResponse;
import com.project.conforzone.auth.JwtService;
import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.UserModel;
import com.project.conforzone.model.dto.LoginRequest;
import com.project.conforzone.model.dto.RegisterRequest;
import com.project.conforzone.model.dto.UserModelDto;
import com.project.conforzone.repository.UserRepository;
import com.project.conforzone.services.AuthService;
import com.project.conforzone.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final Mapper modelMapper;
    /**
     * Maneja el inicio de sesión del usuario, comprobando que sus credenciales son correctas
     * @param request Objeto que contiene el correo y la contraseña que escribe el usuario para iniciar sesión
     * @return Token con la información correspondida del usuario
     */
    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        UserModel user2 = userRepository.findByEmail(request.getEmail()).orElseThrow();
        Map<String, Object> extraClaims = new HashMap<>(); //Declara toda la información que quiere que tenga el token además del correo
        extraClaims.put("idUser", user2.getId());
        extraClaims.put("name", user2.getName());
        extraClaims.put("role", user2.getRole());
        String token = jwtService.getTokenFromService(extraClaims, user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    /**
     * Maneja el registro del usuario, comprobando previamente que el usuario no exista y guardándolo en la bbdd
     * @param request Objeto que contiene los distintos datos del usuario a la hora de registrarse
     * @return Objeto User mapeado a UserDto
     */
    public UserModelDto register(RegisterRequest request){
        boolean userExist;

        userExist = userRepository.findByEmail(request.getEmail()).isPresent();
            if (!userExist) {
                UserModel user = UserModel.builder()
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .name(request.getName())
                        .tlf(request.getTlf())
                        .role(request.getRole())
                        .build();

                userRepository.save(user);
                return modelMapper.toUserDTO(user);
            } else {
                throw new GlobalException("ya existe un usuario con ese correo");
            }
        }
}
