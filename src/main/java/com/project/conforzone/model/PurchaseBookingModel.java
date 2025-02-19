package com.project.conforzone.model;

import com.project.conforzone.util.MoneyConverter;
import jakarta.persistence.*;
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
@Entity
@Table(name = "purchase_booking_table")
public class PurchaseBookingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date datePurchase;

    @Convert(converter = MoneyConverter.class)
    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Integer totalPrice;

    private String address;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserModel userPurchase;

    @OneToMany(mappedBy = "purchaseBooking", fetch = FetchType.EAGER)
    private List<ServiceAdditionalMetersModel> serviceAdditionalMeters;
}
