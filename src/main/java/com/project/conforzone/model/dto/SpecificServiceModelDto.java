package com.project.conforzone.model.dto;

import com.project.conforzone.util.MoneyConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
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
    @Convert(converter = MoneyConverter.class)
    private Integer firstPrice; //Precio real a comprar
    @Convert(converter = MoneyConverter.class)
    private Integer secondPrice; //Precio antes de rebaja
    @Convert(converter = MoneyConverter.class)
    private Integer bookingPrice; //Precio de la reserva
    @Convert(converter = MoneyConverter.class)
    private Integer pricePerMeter;
    private String image;
    private boolean available;

    private List<ServiceAdditionalMetersModelDto> serviceAdditionalMetersDto;
}
