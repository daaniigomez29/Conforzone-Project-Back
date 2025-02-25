package com.project.conforzone.services;

import com.project.conforzone.model.TokenModel;
import com.project.conforzone.model.dto.TokenModelDto;

import java.util.Optional;

public interface TokenService {
    public Optional<TokenModelDto> findByToken(String token);
    public boolean userAlreadyRegister(String email);
    public void addToken(TokenModel token);
    public void deleteByToken(String token);
}
