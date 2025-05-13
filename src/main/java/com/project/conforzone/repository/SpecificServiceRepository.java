package com.project.conforzone.repository;

import com.project.conforzone.model.SpecificServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecificServiceRepository extends JpaRepository<SpecificServiceModel, Integer> {
    public boolean existsByName(String specificServiceName);
    public List<SpecificServiceModel> findByOfferTrue();
    public List<SpecificServiceModel> getSpecificServicesBySlug(String slug);

    @Query("SELECT s FROM SpecificServiceModel s WHERE s.id = :id AND s.slug = :slug")
    public Optional<SpecificServiceModel> getSpecificServiceBySlugAndId(@Param("slug") String slug, @Param("id") Integer id);}
