package com.project.conforzone.controller.impl;

import com.project.conforzone.common.ApiEndpoints;
import com.project.conforzone.controller.ServiceAMController;
import com.project.conforzone.model.dto.ServiceAdditionalMetersModelDto;
import com.project.conforzone.services.ServiceAMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.BASE_SERVICE_AM)
@RequiredArgsConstructor
public class ServiceAMControllerImpl implements ServiceAMController {
    private final ServiceAMService serviceAMService;
    @Override
    @GetMapping()
    public ResponseEntity<List<ServiceAdditionalMetersModelDto>> getAllServicesAM() {
        return ResponseEntity.ok(serviceAMService.getAllServicesAM());
    }

    @Override
    @GetMapping(ApiEndpoints.BASE_ID)
    public ResponseEntity<ServiceAdditionalMetersModelDto> getServiceAMById(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceAMService.getServiceAMById(id));
    }

    @Override
    @PostMapping(ApiEndpoints.BASE_ADD)
    public ResponseEntity<ServiceAdditionalMetersModelDto> addServiceAM(@RequestBody ServiceAdditionalMetersModelDto serviceAdditionalMetersModelDto) {
        return ResponseEntity.ok(serviceAMService.addServiceAM(serviceAdditionalMetersModelDto));
    }
}
