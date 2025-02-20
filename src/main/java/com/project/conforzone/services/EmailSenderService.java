package com.project.conforzone.services;

import com.project.conforzone.model.EmailModel;

public interface EmailSenderService {
    public void sendEmail(EmailModel emailModel);
}
