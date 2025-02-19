package com.project.conforzone.repository;

import com.project.conforzone.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    
    boolean existsByEmailIgnoreCase(String email);
    Optional<UserModel> findByEmail(String email);
}
