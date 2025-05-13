package com.project.conforzone.services;


import com.project.conforzone.model.SpecificServiceModel;
import com.project.conforzone.model.dto.SpecificServiceModelDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecificServiceModelService {
    public List<SpecificServiceModelDto> getAllSpecificServices();
    public List<SpecificServiceModelDto> getAllOfferSpecificServices();
    public List<SpecificServiceModelDto> getSpecificServicesBySlug(String slug);
    public SpecificServiceModelDto getSpecificServiceById(Integer id);
    public SpecificServiceModelDto getOfferSpecificServiceById(Integer id);
    public SpecificServiceModelDto getSpecificServiceBySlugAndId(String slug, Integer id);
    public SpecificServiceModelDto addSpecificService(SpecificServiceModelDto SpecificServiceModelDto);
    public SpecificServiceModelDto editSpecificService(SpecificServiceModelDto SpecificServiceModelDto, Integer idPath);
    // public boolean deleteSpecificServiceById(Integer id);
}
