package com.project.conforzone.controller;

import com.project.conforzone.model.dto.ServiceAdditionalMetersModelDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceAMController {
    public ResponseEntity<List<ServiceAdditionalMetersModelDto>> getAllServicesAM();
    public ResponseEntity<ServiceAdditionalMetersModelDto> getServiceAMById(Integer id);
    public ResponseEntity<ServiceAdditionalMetersModelDto> addServiceAM(ServiceAdditionalMetersModelDto serviceAdditionalMetersModelDto);
}
