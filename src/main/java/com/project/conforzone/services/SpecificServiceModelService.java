package com.project.conforzone.services;


import com.project.conforzone.model.dto.SpecificServiceModelDto;

import java.util.List;

public interface SpecificServiceModelService {
    public List<SpecificServiceModelDto> getAllSpecificServices();
    public SpecificServiceModelDto getSpecificServiceById(Integer id);
    public SpecificServiceModelDto addSpecificService(SpecificServiceModelDto SpecificServiceModelDto);
    public SpecificServiceModelDto editSpecificService(SpecificServiceModelDto SpecificServiceModelDto);
    // public boolean deleteSpecificServiceById(Integer id);
}
