package com.project.conforzone.model;

import com.project.conforzone.util.MoneyConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getter y Setter
@Builder
@NoArgsConstructor //Constructor sin argumentos
@AllArgsConstructor //Constructor con todos los argumentos
@Entity
@Table(name = "service_additional_meters_table")
public class ServiceAdditionalMetersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "specific_service_id")
    private SpecificServiceModel specificService;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "purchase_booking_id")
    private PurchaseBookingModel purchaseBooking;

    private Integer additionalMeters;
    @Convert(converter = MoneyConverter.class)
    @Column(columnDefinition = "NUMERIC(10,2)")
    private Integer priceAtPurchase;
}
