package com.project.conforzone.controller.impl;

import com.project.conforzone.common.ApiEndpoints;
import com.project.conforzone.controller.SpecificServiceController;
import com.project.conforzone.model.dto.SpecificServiceModelDto;
import com.project.conforzone.services.SpecificServiceModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.BASE_SPECIFIC_SERVICE)
@RequiredArgsConstructor
public class SpecificServiceControllerImpl implements SpecificServiceController {
    private final SpecificServiceModelService specificServiceModelService;
    @Override
    @GetMapping()
    public ResponseEntity<List<SpecificServiceModelDto>> getAllSpecificServices() {
        return ResponseEntity.ok(specificServiceModelService.getAllSpecificServices());
    }

    @Override
    @GetMapping(ApiEndpoints.SPECIFIC_SERVICE_OFFER)
    public ResponseEntity<List<SpecificServiceModelDto>> getAllOfferSpecificServices() {
        return ResponseEntity.ok(specificServiceModelService.getAllOfferSpecificServices());
    }

    @Override
    @GetMapping(ApiEndpoints.SPECIFIC_SERVICE_SLASH_SLUG + ApiEndpoints.SPECIFIC_SERVICE_SLUG)
    public ResponseEntity<List<SpecificServiceModelDto>> getSpecificServicesBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(specificServiceModelService.getSpecificServicesBySlug(slug));
    }

    @Override
    @GetMapping(ApiEndpoints.BASE_ID)
    public ResponseEntity<SpecificServiceModelDto> getSpecificServiceById(@PathVariable Integer id) {
        return ResponseEntity.ok(specificServiceModelService.getSpecificServiceById(id));
    }

    @Override
    @GetMapping(ApiEndpoints.BASE_SERVICE + ApiEndpoints.SPECIFIC_SERVICE_OFFER + ApiEndpoints.BASE_ID)
    public ResponseEntity<SpecificServiceModelDto> getOfferSpecificServiceById(@PathVariable Integer id) {
        return ResponseEntity.ok(specificServiceModelService.getOfferSpecificServiceById(id));
    }

    @Override
    @GetMapping(ApiEndpoints.SPECIFIC_SERVICE_SLUG + ApiEndpoints.BASE_ID)
    public ResponseEntity<SpecificServiceModelDto> getSpecificServiceBySlugAndId(@PathVariable String slug, @PathVariable Integer id) {
        return ResponseEntity.ok(specificServiceModelService.getSpecificServiceBySlugAndId(slug, id));
    }

    @Override
    @PostMapping(ApiEndpoints.BASE_ADD)
    public ResponseEntity<SpecificServiceModelDto> addSpecificService(@RequestBody SpecificServiceModelDto specificServiceModelDto) {
        return ResponseEntity.ok(specificServiceModelService.addSpecificService(specificServiceModelDto));
    }

    @Override
    @PutMapping(ApiEndpoints.BASE_EDIT + ApiEndpoints.BASE_ID)
    public ResponseEntity<SpecificServiceModelDto> editSpecificService(@RequestBody SpecificServiceModelDto specificServiceModelDto, @PathVariable Integer id) {
        return ResponseEntity.ok(specificServiceModelService.editSpecificService(specificServiceModelDto, id));
    }
}
