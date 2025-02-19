package com.project.conforzone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data //Getter y Setter
@Builder
@NoArgsConstructor //Constructor sin argumentos
@AllArgsConstructor //Constructor con todos los argumentos
public class PurchaseBookingModelDto {
    private Integer id;
    private Date datePurchase;
    private Integer totalPrice;
    private String address;

    private UserModelDto userPurchaseDto;
    private List<ServiceAdditionalMetersModelDto> serviceAdditionalMettersDto;

}
