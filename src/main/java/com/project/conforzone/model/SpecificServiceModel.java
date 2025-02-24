package com.project.conforzone.model;

import com.project.conforzone.util.MoneyConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //Getter y Setter
@Builder
@NoArgsConstructor //Constructor sin argumentos
@AllArgsConstructor //Constructor con todos los argumentos
@Entity
@Table(name = "specific_service_table")
public class SpecificServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    private String description;

    @Convert(converter = MoneyConverter.class)
    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Integer firstPrice;

    @Convert(converter = MoneyConverter.class)
    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Integer secondPrice;

    @Convert(converter = MoneyConverter.class)
    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Integer bookingPrice;

    @Convert(converter = MoneyConverter.class)
    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Integer pricePerMeter;

    private String image;
    private boolean available;

    @OneToMany(mappedBy = "specificService", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ServiceAdditionalMetersModel> serviceAdditionalMeters;
}
