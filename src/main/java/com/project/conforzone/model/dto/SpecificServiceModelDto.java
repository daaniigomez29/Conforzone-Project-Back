package com.project.conforzone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //Getter y Setter
@Builder
@NoArgsConstructor //Constructor sin argumentos
@AllArgsConstructor //Constructor con todos los argumentos
public class SpecificServiceModelDto {
    private Integer id;
    private String name;
    private String description;
    private Integer firstPrice; //Precio real a comprar
    private Integer secondPrice; //Precio antes de rebaja
    private Integer bookingPrice; //Precio de la reserva
    private Integer pricePerMetter;
    private String image;
    private boolean available;

    private List<ServiceAdditionalMetersModelDto> serviceAdditionalMettersDto;
}
