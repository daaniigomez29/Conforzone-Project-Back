package com.project.conforzone.services.impl;

import com.project.conforzone.model.TokenModel;
import com.project.conforzone.model.dto.*;
import com.project.conforzone.security.JwtService;
import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.UserModel;
import com.project.conforzone.repository.UserRepository;
import com.project.conforzone.services.AuthService;
import com.project.conforzone.services.EmailSenderService;
import com.project.conforzone.services.TokenService;
import com.project.conforzone.util.Mapper;
import com.project.conforzone.util.PatternEmail;
import jakarta.mail.MessagingException;
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
    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final Mapper modelMapper;
    private final EmailSenderService emailSenderService;
    private final UserCacheService userCacheService;

    /**
     * Maneja el inicio de sesión del usuario, comprobando que sus credenciales son correctas
     *
     * @param request Objeto que contiene el correo y la contraseña que escribe el usuario para iniciar sesión
     * @return Token con la información correspondida del usuario
     */
    public AuthResponse login(LoginRequest request) {
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
     *
     * @param request Objeto que contiene los distintos datos del usuario a la hora de registrarse
     * @return Objeto User mapeado a UserDto
     */
    public TokenModelDto register(RegisterRequest request) throws MessagingException {
        boolean userExist;
        TokenModel tokenModel = new TokenModel();

        if (PatternEmail.isValidEmail(request.getEmail())) {
            userExist = userRepository.findByEmail(request.getEmail()).isPresent();
            if (!userExist) {
                String tokenRegister = jwtService.getTokenToRegisterFromService(request);
                if (!tokenService.userAlreadyRegister(request.getEmail())) {
                    tokenModel.setToken(tokenRegister);
                    tokenModel.setEmail(request.getEmail());
                    tokenService.addToken(tokenModel);

                    userCacheService.storeUserData(request.getEmail(), request);

                    String urlConfirmation = "https://conforzone-project-back-production.up.railway.app/api/v1/auth/confirm-register/" + tokenRegister;
                    emailSenderService.sendEmailRegistration(request.getEmail(), request.getName(), urlConfirmation);

                    return modelMapper.toTokenModelDto(tokenModel);
                } else {
                    throw new GlobalException("El usuario ya se ha registrado previamente");
                }
            } else {
                throw new GlobalException("Ya existe un usuario con ese correo");
            }
        } else {
            throw new GlobalException("El correo no es válido");
        }
    }

    @Override
    public void confirmRegister(String token) throws MessagingException {
        if (tokenService.findByToken(token).isPresent()) {
            String userEmail = jwtService.getUsernameFromToken(token);
            RegisterRequest request = userCacheService.getRegisterRequest(userEmail);
            UserModel user = UserModel.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .lastName(request.getLastName())
                    .address(request.getAddress())
                    .tlf(request.getTlf())
                    .role(request.getRole())
                    .build();

            userRepository.save(user);
            tokenService.deleteByToken(token);
            userCacheService.removeUserData(userEmail);
        } else {
            throw new GlobalException("El token de registro no existe");
        }
    }
}
