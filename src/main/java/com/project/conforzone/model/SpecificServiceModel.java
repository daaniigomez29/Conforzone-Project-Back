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
    @Column(length = 9999)
    private String description;
    private String slug;

    //@Convert(converter = MoneyConverter.class)
    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Double firstPrice;

    //@Convert(converter = MoneyConverter.class)
    @Column(columnDefinition = "NUMERIC(10,2)")
    private Double secondPrice;

    //@Convert(converter = MoneyConverter.class)
    @Column(columnDefinition = "NUMERIC(10,2)")
    private Double bookingPrice;

    //@Convert(converter = MoneyConverter.class)
    @Column(columnDefinition = "NUMERIC(10,2)")
    private Double pricePerMeter;

    private String image;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean available;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean offer;

    @OneToMany(mappedBy = "specificService", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ServiceAdditionalMetersModel> serviceAdditionalMeters;
}
