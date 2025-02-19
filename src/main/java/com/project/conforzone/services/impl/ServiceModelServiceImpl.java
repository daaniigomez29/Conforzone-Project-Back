package com.project.conforzone.services.impl;

import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.ServiceModel;
import com.project.conforzone.model.dto.ServiceModelDto;
import com.project.conforzone.repository.ServiceRepository;
import com.project.conforzone.services.ServiceModelService;
import com.project.conforzone.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ServiceModelServiceImpl implements ServiceModelService {
    private final ServiceRepository serviceRepository;
    private final Mapper modelMapper;

    @Override
    public List<ServiceModelDto> getAllServices() {
        return serviceRepository.findAll().stream().map(serviceModel -> modelMapper.toServiceModelDto(serviceModel)).collect(Collectors.toList());
    }

    @Override
    public ServiceModelDto getServiceById(Integer id) {
        return serviceRepository.findById(id).map(modelMapper::toServiceModelDto).orElseThrow(() -> new GlobalException("El servicio no existe"));
    }

    @Override
    public ServiceModelDto addService(ServiceModelDto serviceModelDto) {
        if (!serviceRepository.findByName(serviceModelDto.getName())){
            return modelMapper.toServiceModelDto(serviceRepository.save(modelMapper.toServiceModel(serviceModelDto)));
        } else {
          throw new GlobalException("El servicio ya existe");
        }
    }

    @Override
    public ServiceModelDto editService(ServiceModelDto serviceModelDto) {
        ServiceModel serviceModel = serviceRepository.findById(serviceModelDto.getId()).orElseThrow(() -> new GlobalException("El servicio no existe"));
        return modelMapper.toServiceModelDto(serviceRepository.save(serviceModel));
    }
}
