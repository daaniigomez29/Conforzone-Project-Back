package com.project.conforzone.services.impl;

import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.UserModel;
import com.project.conforzone.model.dto.*;
import com.project.conforzone.repository.PurchaseBookingRepository;
import com.project.conforzone.repository.UserRepository;
import com.project.conforzone.services.PurchaseBookingService;
import com.project.conforzone.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseBookingServiceImpl implements PurchaseBookingService {
    private final PurchaseBookingRepository purchaseBookingRepository;
    private final UserRepository userRepository;
    private final Mapper modelMapper;
    private final ServiceAMServiceImpl serviceAMServiceImpl;

    @Override
    public List<PurchaseBookingModelDto> getAllPurchases() {
        return purchaseBookingRepository.findAll().stream().map(modelMapper::toPurchaseBookingModelDto).collect(Collectors.toList());
    }

    @Override
    public PurchaseBookingModelDto getPurchaseById(Integer id) {
        return purchaseBookingRepository.findById(id).map(modelMapper::toPurchaseBookingModelDto).orElseThrow(() -> new GlobalException("La compra no existe"));
    }

    @Override
    public PurchaseBookingModelDto addPurchase(Integer userId, PurchaseBookingModelDto purchase, List<ServicePurchaseRequest> servicePurchaseRequests) {
        UserModelDto userModelDto = modelMapper.toUserDTO(userRepository.findById(userId).orElseThrow(() -> new GlobalException("El usuario no existe")));
        purchase.setDatePurchase(new Date());
        purchase.setUserPurchaseDto(userModelDto);

        purchaseBookingRepository.save(modelMapper.toPurchaseBookingModel(purchase));

        List<ServiceAdditionalMetersModelDto> serviceAdditionalMetersDtoList = new ArrayList<>();
        int totalPrice = 0;

        for (ServicePurchaseRequest servicePurchaseRequest : servicePurchaseRequests) {
            SpecificServiceModelDto specificServiceToBuy = servicePurchaseRequest.getSpecificServiceModelDto();
            int additionalMeters = servicePurchaseRequest.getAdditionalMeters();
            if (specificServiceToBuy.isAvailable()) {
                ServiceAdditionalMetersModelDto serviceToBuy = new ServiceAdditionalMetersModelDto();
                int priceAtPurchase = specificServiceToBuy.getFirstPrice();
                //Comprobar que luego se le asigna un id a cada entidad en la bbdd

                serviceToBuy.setSpecificServiceDto(specificServiceToBuy);
                serviceToBuy.setAdditionalMeters(additionalMeters);
                if (additionalMeters > 0) {
                    priceAtPurchase = (specificServiceToBuy.getPricePerMetter() * additionalMeters) + specificServiceToBuy.getFirstPrice();
                    serviceToBuy.setPriceAtPurchase(priceAtPurchase);
                }
                serviceToBuy.setPriceAtPurchase(priceAtPurchase);
                serviceToBuy.setPurchaseBookingDto(purchase); //Asigna el servicio con sus metros adicionales a la compra
                totalPrice += priceAtPurchase;
                serviceAdditionalMetersDtoList.add(serviceToBuy);
                serviceAMServiceImpl.addServiceAM(serviceToBuy);
            }
        }
        //purchase.setServiceAdditionalMettersDto(serviceAdditionalMetersDtoList);
        purchase.setTotalPrice(totalPrice);

        purchaseBookingRepository.save(modelMapper.toPurchaseBookingModel(purchase));
        return purchase;
    }

    @Override
    public List<PurchaseBookingModelDto> getAllUserPurchases(Integer userId) {
        UserModel userModel = userRepository.findById(userId).orElseThrow(() -> new GlobalException("El usuario no existe"));
        return purchaseBookingRepository.getAllUserPurchases(userModel).stream().map(modelMapper::toPurchaseBookingModelDto).toList();
    }
}
