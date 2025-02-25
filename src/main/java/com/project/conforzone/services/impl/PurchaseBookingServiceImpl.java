package com.project.conforzone.services.impl;

import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.PurchaseBookingModel;
import com.project.conforzone.model.ServiceAdditionalMetersModel;
import com.project.conforzone.model.SpecificServiceModel;
import com.project.conforzone.model.UserModel;
import com.project.conforzone.model.dto.*;
import com.project.conforzone.repository.PurchaseBookingRepository;
import com.project.conforzone.repository.ServiceAMRepository;
import com.project.conforzone.repository.SpecificServiceRepository;
import com.project.conforzone.repository.UserRepository;
import com.project.conforzone.services.PurchaseBookingService;
import com.project.conforzone.services.ServiceAMService;
import com.project.conforzone.util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseBookingServiceImpl implements PurchaseBookingService {
    private final PurchaseBookingRepository purchaseBookingRepository;
    private final UserRepository userRepository;
    private final ServiceAMRepository serviceAMRepository;
    private final Mapper modelMapper;
    private final SpecificServiceRepository specificServiceRepository;

    @Override
    public List<PurchaseBookingModelDto> getAllPurchases() {
        return purchaseBookingRepository.findAll().stream().map(modelMapper::toPurchaseBookingModelDto).collect(Collectors.toList());
    }

    @Override
    public PurchaseBookingModelDto getPurchaseById(Integer id) {
        return purchaseBookingRepository.findById(id).map(modelMapper::toPurchaseBookingModelDto).orElseThrow(() -> new GlobalException("La compra no existe"));
    }

    @Transactional(rollbackOn = Exception.class) //Asegura que la compra no se guarde en la bbdd cuando haya cualquier Exception
    @Override
    public PurchaseBookingModelDto addPurchase(Integer userId, List<ServicePurchaseRequest> servicePurchaseRequests) {
        UserModel userModel = userRepository.findById(userId).orElseThrow(() -> new GlobalException("El usuario no existe"));
        PurchaseBookingModel purchase = new PurchaseBookingModel();
        purchase.setDatePurchase(LocalDateTime.now());
        purchase.setUserPurchase(userModel);
        purchase.setAddress(userModel.getAddress());

        purchase = purchaseBookingRepository.save(purchase);

        List<ServiceAdditionalMetersModel> serviceAdditionalMetersList = new ArrayList<>();
        int totalPrice = 0;
        int bookingTotalPrice = 0;

        for (ServicePurchaseRequest servicePurchaseRequest : servicePurchaseRequests) {
            SpecificServiceModel specificServiceToBuy = specificServiceRepository.findById(servicePurchaseRequest.getSpecificServiceModel().getId()).orElseThrow(() -> new GlobalException("El servicio específico no se encuentra"));
            int additionalMeters = servicePurchaseRequest.getAdditionalMeters();
            if (specificServiceToBuy.isAvailable()) {
                ServiceAdditionalMetersModel serviceToBuy = new ServiceAdditionalMetersModel();

                int priceAtPurchase = specificServiceToBuy.getFirstPrice();
                //Comprobar que luego se le asigna un id a cada entidad en la bbdd

                serviceToBuy.setSpecificService(specificServiceToBuy);
                serviceToBuy.setAdditionalMeters(additionalMeters);
                if (additionalMeters > 0) {
                    priceAtPurchase = (specificServiceToBuy.getPricePerMeter() * additionalMeters) + specificServiceToBuy.getFirstPrice();
                    serviceToBuy.setPriceAtPurchase(priceAtPurchase);
                }
                serviceToBuy.setPriceAtPurchase(priceAtPurchase);
                serviceToBuy.setPurchaseBooking(purchase); //Asigna el servicio con sus metros adicionales a la compra

                totalPrice += priceAtPurchase;
                bookingTotalPrice += specificServiceToBuy.getBookingPrice();
                serviceAdditionalMetersList.add(serviceToBuy);
                serviceAMRepository.save(serviceToBuy);
            } else {
                throw new GlobalException("El servicio no se encuentra disponible");
            }
        }
        purchase.setServiceAdditionalMeters(serviceAdditionalMetersList);
        purchase.setTotalPrice(totalPrice);
        purchase.setBookingTotalPrice(bookingTotalPrice);

        purchaseBookingRepository.save(purchase);
        return modelMapper.toPurchaseBookingModelDto(purchase);
    }

    @Transactional
    @Override
    public List<PurchaseBookingModelDto> getAllUserPurchases(Integer userId) {
        UserModel userModel = userRepository.findById(userId).orElseThrow(() -> new GlobalException("El usuario no existe"));
        return purchaseBookingRepository.getAllUserPurchases(userModel).stream().map(modelMapper::toPurchaseBookingModelDto).toList();
    }
}
