package com.project.conforzone.services.impl;

import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.TokenModel;
import com.project.conforzone.model.dto.TokenModelDto;
import com.project.conforzone.repository.TokenRepository;
import com.project.conforzone.security.JwtService;
import com.project.conforzone.services.TokenService;
import com.project.conforzone.util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final Mapper mapper;
    @Override
    public Optional<TokenModelDto> findByToken(String token) {
        TokenModel tokenSearch = tokenRepository.findByToken(token);

        if(tokenSearch != null){
            return Optional.of(mapper.toTokenModelDto(tokenSearch));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<TokenModelDto> findByEmail(String email) {
        TokenModel tokenSearch = tokenRepository.findByEmail(email);

        if(tokenSearch != null){
            return Optional.of(mapper.toTokenModelDto(tokenSearch));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean userAlreadyRegister(String email) {
        return tokenRepository.existsByEmail(email);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return jwtService.isTokenRegisterValid(token);
    }

    @Override
    public void addToken(TokenModel token) {
        if (findByToken(token.getToken()).isEmpty()) {
            tokenRepository.save(token);
        }
    }
    @Transactional
    @Override
    public void deleteByToken(String token) {
        tokenRepository.deleteByToken(token);
    }
}
