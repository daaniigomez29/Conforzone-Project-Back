package com.project.conforzone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getter y Setter
@Builder
@NoArgsConstructor //Constructor sin argumentos
@AllArgsConstructor //Constructor con todos los argumentos
public class ServiceAdditionalMetersModelDto {
    private Integer id;

    private SpecificServiceModelDto specificServiceDto;

    private PurchaseBookingModelDto purchaseBookingDto;

    private Integer additionalMeters;
    private Integer priceAtPurchase;
}
