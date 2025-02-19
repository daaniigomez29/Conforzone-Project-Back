package com.project.conforzone.services.impl;

import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.dto.ServiceAdditionalMetersModelDto;
import com.project.conforzone.repository.ServiceAMRepository;
import com.project.conforzone.services.ServiceAMService;
import com.project.conforzone.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ServiceAMServiceImpl implements ServiceAMService {
    private final ServiceAMRepository serviceAMRepository;
    private final Mapper modelMapper;
    @Override
    public List<ServiceAdditionalMetersModelDto> getAllServicesAM() {
        return null;
    }

    @Override
    public ServiceAdditionalMetersModelDto getServiceAMById(Integer id) {
        return serviceAMRepository.findById(id).map(modelMapper::toServiceAdditionalMettersModelDto).orElseThrow(() -> new GlobalException("El servicio comprado con o sin metros adicionales no existe."));
    }

    @Override
    public ServiceAdditionalMetersModelDto addServiceAM(ServiceAdditionalMetersModelDto serviceAdditionalMettersModelDto) {
        if (serviceAdditionalMettersModelDto.getSpecificServiceDto() != null && serviceAdditionalMettersModelDto.getPurchaseBookingDto() != null){
           return modelMapper.toServiceAdditionalMettersModelDto(serviceAMRepository.save(modelMapper.toServiceAdditionalMettersModel(serviceAdditionalMettersModelDto)));
        } else {
            throw new GlobalException("La compra no se ha realizado");
        }
    }

    @Override
    public ServiceAdditionalMetersModelDto editServiceAM(ServiceAdditionalMetersModelDto serviceAdditionalMettersModelDto) {
        return null;
    }
}
