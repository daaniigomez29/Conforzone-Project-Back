package com.project.conforzone.controller.impl;

import com.project.conforzone.common.ApiEndpoints;
import com.project.conforzone.controller.PurchaseBookingController;
import com.project.conforzone.model.dto.PurchaseBookingModelDto;
import com.project.conforzone.model.dto.ServicePurchaseRequest;
import com.project.conforzone.services.PurchaseBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.BASE_PURCHASE_BOOKING)
@RequiredArgsConstructor
public class PurchaseBookingControllerImpl implements PurchaseBookingController {
    private final PurchaseBookingService purchaseBookingService;
    @Override
    @GetMapping()
    public ResponseEntity<List<PurchaseBookingModelDto>> getAllPurchases() {
        return null;
    }

    @Override
    @GetMapping(ApiEndpoints.BASE_ID)
    public ResponseEntity<PurchaseBookingModelDto> getPurchaseById(@PathVariable Integer id) {
        return ResponseEntity.ok(purchaseBookingService.getPurchaseById(id));
    }

    @Override
    @PostMapping(ApiEndpoints.PURCHASE)
    public ResponseEntity<PurchaseBookingModelDto> addPurchase(@PathVariable Integer userId, @RequestBody List<ServicePurchaseRequest> servicePurchaseRequests) {
        return ResponseEntity.ok(purchaseBookingService.addPurchase(userId, servicePurchaseRequests));
    }

    @Override
    @GetMapping(ApiEndpoints.USER_PURCHASES)
    public ResponseEntity<List<PurchaseBookingModelDto>> getAllUserPurchases(@PathVariable Integer userId) {
        return ResponseEntity.ok(purchaseBookingService.getAllUserPurchases(userId));
    }
}
