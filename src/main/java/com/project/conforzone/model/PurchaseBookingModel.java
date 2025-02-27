package com.project.conforzone.model;

import com.project.conforzone.util.MoneyConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private String transactionId;
    private LocalDateTime datePurchase;

    //@Convert(converter = MoneyConverter.class)
    @Column(columnDefinition = "NUMERIC(10,2)")
    private Double totalPrice;

    //@Convert(converter = MoneyConverter.class)
    @Column(columnDefinition = "NUMERIC(10,2)")
    private Double bookingTotalPrice;

    private String address;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserModel userPurchase;

    @OneToMany(mappedBy = "purchaseBooking", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ServiceAdditionalMetersModel> serviceAdditionalMeters;
}
