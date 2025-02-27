package com.project.conforzone.services;


import com.project.conforzone.model.dto.SpecificServiceModelDto;

import java.util.List;

public interface SpecificServiceModelService {
    public List<SpecificServiceModelDto> getAllSpecificServices();
    public List<SpecificServiceModelDto> getAllOfferSpecificServices();
    public SpecificServiceModelDto getSpecificServiceById(Integer id);
    public SpecificServiceModelDto addSpecificService(SpecificServiceModelDto SpecificServiceModelDto);
    public SpecificServiceModelDto editSpecificService(SpecificServiceModelDto SpecificServiceModelDto, Integer idPath);
    // public boolean deleteSpecificServiceById(Integer id);
}
