package com.project.conforzone.services.impl;

import com.project.conforzone.model.EmailModel;
import com.project.conforzone.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;

    @Value("spring.mail.username")
    private String emailSender;
    @Override
    public void sendEmail(EmailModel emailModel) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSender);
        message.setTo(emailModel.getToEmail());
        message.setText(emailModel.getBody());
        message.setSubject(emailModel.getSubject());

        javaMailSender.send(message);

        System.out.println("Email enviado correctamente");
    }
}
