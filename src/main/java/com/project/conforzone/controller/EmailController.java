package com.project.conforzone.controller;

import com.project.conforzone.model.dto.EmailModel;
import org.springframework.http.ResponseEntity;

public interface EmailController {
    public void sendEmailContact(EmailModel emailModel);
}
