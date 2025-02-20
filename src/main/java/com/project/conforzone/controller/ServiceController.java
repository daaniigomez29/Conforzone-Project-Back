package com.project.conforzone.controller;

import com.project.conforzone.model.dto.ServiceModelDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceController {
    public ResponseEntity<List<ServiceModelDto>> getAllServices();
    public ResponseEntity<ServiceModelDto> getServiceById(Integer id);
    public ResponseEntity<ServiceModelDto> addService(ServiceModelDto serviceModelDto);
    public ResponseEntity<ServiceModelDto> editService(ServiceModelDto serviceModelDto);
}
