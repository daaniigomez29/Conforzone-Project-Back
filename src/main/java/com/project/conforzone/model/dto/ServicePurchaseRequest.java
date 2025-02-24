package com.project.conforzone.model.dto;

import com.project.conforzone.model.SpecificServiceModel;
import lombok.Data;

@Data
public class ServicePurchaseRequest {

    private SpecificServiceModel specificServiceModel;
    private Integer additionalMeters;
}