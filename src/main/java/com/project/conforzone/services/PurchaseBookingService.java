package com.project.conforzone.services;

import com.project.conforzone.model.UserModel;
import com.project.conforzone.model.dto.PurchaseBookingModelDto;
import com.project.conforzone.model.dto.ServicePurchaseRequest;
import com.project.conforzone.model.dto.SpecificServiceModelDto;

import java.util.List;

public interface PurchaseBookingService {
    public List<PurchaseBookingModelDto> getAllPurchases();
    public PurchaseBookingModelDto getPurchaseById(Integer id);
    public PurchaseBookingModelDto addPurchase(Integer userId, List<ServicePurchaseRequest> servicePurchaseRequests);
    public List<PurchaseBookingModelDto> getAllUserPurchases(Integer userId);

    // public PurchaseBookingModelDto editPurchase(PurchaseBookingModelDto purchaseBookingModelDto);
    // public boolean deletePurchaseById(Integer id);
}
