package com.project.conforzone.controller.impl;

import com.project.conforzone.common.ApiEndpoints;
import com.project.conforzone.controller.ServiceController;
import com.project.conforzone.model.dto.ServiceModelDto;
import com.project.conforzone.services.ServiceModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.BASE_SERVICE)
@RequiredArgsConstructor
public class ServiceControllerImpl implements ServiceController {
    private final ServiceModelService serviceModelService;
    @Override
    @GetMapping()
    public ResponseEntity<List<ServiceModelDto>> getAllServices() {
        return ResponseEntity.ok(serviceModelService.getAllServices());
    }

    @Override
    @GetMapping(ApiEndpoints.BASE_ID)
    public ResponseEntity<ServiceModelDto> getServiceById(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceModelService.getServiceById(id));
    }

    @Override
    @PostMapping(ApiEndpoints.BASE_ADD)
    public ResponseEntity<ServiceModelDto> addService(@RequestBody ServiceModelDto serviceModelDto) {
        return ResponseEntity.ok(serviceModelService.addService(serviceModelDto));
    }

    @Override
    @PutMapping(ApiEndpoints.BASE_EDIT + ApiEndpoints.BASE_ID)
    public ResponseEntity<ServiceModelDto> editService(@RequestBody ServiceModelDto serviceModelDto) {
        return ResponseEntity.ok(serviceModelService.editService(serviceModelDto));
    }
}
