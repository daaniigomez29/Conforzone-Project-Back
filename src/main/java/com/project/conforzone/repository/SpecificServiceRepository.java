package com.project.conforzone.repository;

import com.project.conforzone.model.SpecificServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificServiceRepository extends JpaRepository<SpecificServiceModel, Integer> {
    public boolean existsByName(String specificServiceName);
    public List<SpecificServiceModel> findByOfferTrue();
}
