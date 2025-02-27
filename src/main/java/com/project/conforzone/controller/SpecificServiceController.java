package com.project.conforzone.controller;

import com.project.conforzone.model.dto.SpecificServiceModelDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpecificServiceController {
    public ResponseEntity<List<SpecificServiceModelDto>> getAllSpecificServices();
    public ResponseEntity<List<SpecificServiceModelDto>> getAllOfferSpecificServices();
    public ResponseEntity<SpecificServiceModelDto> getSpecificServiceById(Integer id);
    public ResponseEntity<SpecificServiceModelDto> addSpecificService(SpecificServiceModelDto specificServiceModelDto);
    public ResponseEntity<SpecificServiceModelDto> editSpecificService(SpecificServiceModelDto specificServiceModelDto, Integer idPath);
}
