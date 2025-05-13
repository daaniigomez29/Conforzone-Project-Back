package com.project.conforzone.model.dto;

import com.project.conforzone.util.MoneyConverter;
import jakarta.persistence.Convert;
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

    private Integer additionalMeters;
    @Convert(converter = MoneyConverter.class)
    private Integer priceAtPurchase;
}
