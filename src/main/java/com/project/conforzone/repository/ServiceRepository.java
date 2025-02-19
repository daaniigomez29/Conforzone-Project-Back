package com.project.conforzone.repository;

import com.project.conforzone.model.ServiceModel;
import com.project.conforzone.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, Integer> {
    public boolean findByName(String serviceName);
}
