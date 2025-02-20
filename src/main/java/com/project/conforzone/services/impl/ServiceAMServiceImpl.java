package com.project.conforzone.services.impl;

import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.dto.ServiceAdditionalMetersModelDto;
import com.project.conforzone.repository.ServiceAMRepository;
import com.project.conforzone.services.ServiceAMService;
import com.project.conforzone.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceAMServiceImpl implements ServiceAMService {
    private final ServiceAMRepository serviceAMRepository;
    private final Mapper modelMapper;
    @Override
    public List<ServiceAdditionalMetersModelDto> getAllServicesAM() {
        return serviceAMRepository.findAll().stream().map(modelMapper::toServiceAdditionalMettersModelDto).collect(Collectors.toList());
    }

    @Override
    public ServiceAdditionalMetersModelDto getServiceAMById(Integer id) {
        return serviceAMRepository.findById(id).map(modelMapper::toServiceAdditionalMettersModelDto).orElseThrow(() -> new GlobalException("El servicio comprado con o sin metros adicionales no existe."));
    }

    @Override
    public ServiceAdditionalMetersModelDto addServiceAM(ServiceAdditionalMetersModelDto serviceAdditionalMetersModelDto) {
        if (serviceAdditionalMetersModelDto.getSpecificServiceDto() != null && serviceAdditionalMetersModelDto.getPurchaseBookingDto() != null){
           return modelMapper.toServiceAdditionalMettersModelDto(serviceAMRepository.save(modelMapper.toServiceAdditionalMettersModel(serviceAdditionalMetersModelDto)));
        } else {
            throw new GlobalException("La compra no se ha realizado");
        }
    }
}
