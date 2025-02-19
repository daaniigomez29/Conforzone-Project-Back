package com.project.conforzone.repository;

import com.project.conforzone.model.SpecificServiceModel;
import com.project.conforzone.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecificServiceRepository extends JpaRepository<SpecificServiceModel, Integer> {
    public boolean findByName(String specificServiceName);
}
