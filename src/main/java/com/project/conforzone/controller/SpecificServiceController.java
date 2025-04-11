package com.project.conforzone.controller;

import com.project.conforzone.model.SpecificServiceModel;
import com.project.conforzone.model.dto.SpecificServiceModelDto;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpecificServiceController {
    public ResponseEntity<List<SpecificServiceModelDto>> getAllSpecificServices();
    public ResponseEntity<List<SpecificServiceModelDto>> getAllOfferSpecificServices();
    public ResponseEntity<List<SpecificServiceModelDto>> getSpecificServicesBySlug(String slug);
    public ResponseEntity<SpecificServiceModelDto> getSpecificServiceById(Integer id);
    public ResponseEntity<SpecificServiceModelDto> getSpecificServiceBySlugAndId(String slug, Integer id);
    public ResponseEntity<SpecificServiceModelDto> addSpecificService(SpecificServiceModelDto specificServiceModelDto);
    public ResponseEntity<SpecificServiceModelDto> editSpecificService(SpecificServiceModelDto specificServiceModelDto, Integer idPath);
}
