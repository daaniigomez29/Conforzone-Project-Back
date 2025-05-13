package com.project.conforzone.controller.impl;

import com.project.conforzone.common.ApiEndpoints;
import com.project.conforzone.controller.EmailController;
import com.project.conforzone.model.dto.EmailModel;
import com.project.conforzone.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.BASE_EMAIL)
public class EmailControllerImpl implements EmailController {
    private final EmailSenderService emailSenderService;
    @Override
    @PostMapping(ApiEndpoints.CONTACT_EMAIL)
    public void sendEmailContact(@RequestBody EmailModel emailModel) {
        emailSenderService.sendEmailContact(emailModel);
    }
}
