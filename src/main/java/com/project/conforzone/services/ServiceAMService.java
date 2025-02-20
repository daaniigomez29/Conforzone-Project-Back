package com.project.conforzone.services;


import com.project.conforzone.model.dto.ServiceAdditionalMetersModelDto;

import java.util.List;

public interface ServiceAMService {
    public List<ServiceAdditionalMetersModelDto> getAllServicesAM();
    public ServiceAdditionalMetersModelDto getServiceAMById(Integer id);
    public ServiceAdditionalMetersModelDto addServiceAM(ServiceAdditionalMetersModelDto ServiceAdditionalMettersModelDto);
    //public ServiceAdditionalMetersModelDto editServiceAM(ServiceAdditionalMetersModelDto ServiceAdditionalMettersModelDto);
    // public boolean deleteServiceAMById(Integer id);
}
