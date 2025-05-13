package com.project.conforzone.repository;

import com.project.conforzone.model.ServiceAdditionalMetersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAMRepository extends JpaRepository<ServiceAdditionalMetersModel, Integer> {

}
