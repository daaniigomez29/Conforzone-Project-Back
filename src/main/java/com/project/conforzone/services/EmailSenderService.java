package com.project.conforzone.services;

import com.project.conforzone.model.TokenModel;
import com.project.conforzone.model.dto.PurchaseBookingModelDto;
import jakarta.mail.MessagingException;

public interface EmailSenderService {
    public void sendEmailRegistration(String toEmail, String nameUser, String urlConfirmation) throws MessagingException;

    public void sendEmailConfirmedPurchase(String toEmail, String nameUser, PurchaseBookingModelDto purchaseBookingModelDto);
}
