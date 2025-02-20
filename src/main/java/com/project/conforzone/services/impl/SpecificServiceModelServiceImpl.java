package com.project.conforzone.services.impl;

import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.SpecificServiceModel;
import com.project.conforzone.model.dto.SpecificServiceModelDto;
import com.project.conforzone.repository.SpecificServiceRepository;
import com.project.conforzone.services.SpecificServiceModelService;
import com.project.conforzone.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecificServiceModelServiceImpl implements SpecificServiceModelService {
    private final SpecificServiceRepository specificServiceRepository;
    private final Mapper modelMapper;
    @Override
    public List<SpecificServiceModelDto> getAllSpecificServices() {
        return specificServiceRepository.findAll().stream().map(modelMapper::toSpecificModelDto).toList();
    }

    @Override
    public SpecificServiceModelDto getSpecificServiceById(Integer id) {
        return specificServiceRepository.findById(id).map(modelMapper::toSpecificModelDto).orElseThrow(() -> new GlobalException("El servicio específico no existe"));
    }

    @Override
    public SpecificServiceModelDto addSpecificService(SpecificServiceModelDto specificServiceModelDto) {
        if (specificServiceRepository.findByName(specificServiceModelDto.getName())){
            return modelMapper.toSpecificModelDto(specificServiceRepository.save(modelMapper.toSpecificModel(specificServiceModelDto)));
        } else {
            throw new GlobalException("El servicio específico ya existe");
        }
    }

    @Override
    public SpecificServiceModelDto editSpecificService(SpecificServiceModelDto specificServiceModelDto) {
        SpecificServiceModel editedSpecificModel = specificServiceRepository.findById(specificServiceModelDto.getId()).orElse(null);
        if (editedSpecificModel != null){
            editedSpecificModel.setName(specificServiceModelDto.getName());
            editedSpecificModel.setBookingPrice(specificServiceModelDto.getBookingPrice());
            editedSpecificModel.setFirstPrice(specificServiceModelDto.getFirstPrice());
            editedSpecificModel.setAvailable(specificServiceModelDto.isAvailable());
            editedSpecificModel.setPricePerMetter(specificServiceModelDto.getPricePerMetter());
            return modelMapper.toSpecificModelDto(specificServiceRepository.save(editedSpecificModel));
        } else {
            throw new GlobalException("El servicio específico no existe");
        }
    }
}
