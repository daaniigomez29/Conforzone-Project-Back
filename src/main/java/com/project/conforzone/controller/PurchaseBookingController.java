package com.project.conforzone.controller;

import com.project.conforzone.model.dto.PurchaseBookingModelDto;
import com.project.conforzone.model.dto.ServicePurchaseRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PurchaseBookingController {
    public ResponseEntity<List<PurchaseBookingModelDto>> getAllPurchases();
    public ResponseEntity<PurchaseBookingModelDto> getPurchaseById(Integer id);
    public ResponseEntity<PurchaseBookingModelDto> addPurchase(Integer userId, PurchaseBookingModelDto purchaseBookingModelDto, List<ServicePurchaseRequest> servicePurchaseRequests);
    public ResponseEntity<List<PurchaseBookingModelDto>> getAllUserPurchases(Integer userId);
}
