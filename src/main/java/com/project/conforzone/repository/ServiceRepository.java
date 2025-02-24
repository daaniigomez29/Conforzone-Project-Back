package com.project.conforzone.repository;

import com.project.conforzone.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, Integer> {
    public boolean existsByName(String serviceName);
}
