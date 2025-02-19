package com.project.conforzone.model.dto;

import lombok.Data;

@Data
public class ServicePurchaseRequest {

    private SpecificServiceModelDto specificServiceModelDto;
    private Integer additionalMeters;
}
