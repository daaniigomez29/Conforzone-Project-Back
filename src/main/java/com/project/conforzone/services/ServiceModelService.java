package com.project.conforzone.services;


import com.project.conforzone.model.dto.ServiceModelDto;

import java.util.List;

public interface ServiceModelService {
    public List<ServiceModelDto> getAllServices();
    public ServiceModelDto getServiceById(Integer id);
    public ServiceModelDto addService(ServiceModelDto ServiceModelDto);
    public ServiceModelDto editService(ServiceModelDto ServiceModelDto);
    // public boolean deleteServiceById(Integer id);
}
