package com.project.conforzone.repository;

import com.project.conforzone.model.TokenModel;
import com.project.conforzone.model.dto.TokenModelDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenModel, Integer> {
    public TokenModel findByToken(String token);
    public TokenModel findByEmail(String email);
    public boolean existsByEmail(String email);
    public void deleteByToken(String token);
}
