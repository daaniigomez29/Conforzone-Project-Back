package com.project.conforzone.services.impl;

import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.TokenModel;
import com.project.conforzone.model.dto.EmailModel;
import com.project.conforzone.model.dto.PurchaseBookingModelDto;
import com.project.conforzone.services.EmailSenderService;
import com.project.conforzone.util.PatternEmail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;
    @Value("${spring.mail.username}")
    private String emailSender;
    @Override
    public void sendEmailRegistration(String toEmail, String nameUser, String urlConfirmation) {
        if (PatternEmail.isValidEmail(toEmail)) {
            try {
                Context context = new Context();
                context.setVariable("userName", nameUser);
                context.setVariable("urlConfirmation", urlConfirmation);

                String body = springTemplateEngine.process("welcomeRegister", context);

                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                helper.setTo(toEmail);
                helper.setSubject("Confirmación de registro");

                helper.setText(body, true);
                javaMailSender.send(mimeMessage);
            } catch (MessagingException messagingException) {
                System.err.println("❌ Error al enviar el correo a: " + toEmail);
                System.err.println("Detalles: " + messagingException.getMessage());
            }
        } else {
            System.out.println("El correo: " + toEmail + " no es válido");
            //throw new GlobalException("El correo no es válido");
        }
    }

    @Override
    public void sendEmailConfirmedPurchase(String toEmail, String nameUser, PurchaseBookingModelDto purchaseBookingModelDto) {

    }

    @Override
    public void sendEmailContact(EmailModel emailModel) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailSender); // El correo al que yo recibo los mensajes
            helper.setSubject(emailModel.getSubject().getLabel());
            helper.setReplyTo(emailModel.getFromEmail());

            helper.setText(
                    "<p>Has recibido un nuevo mensaje:</p>" +
                            "<p>Email del cliente: " + emailModel.getFromEmail() + "</p>" +
                            "<p>Asunto: <b>" + emailModel.getSubject().getLabel() + "</b></p>" +
                            "<p><strong>Mensaje:</strong><br>" + emailModel.getBody() + "</p>",
                    true //Este segundo parámetro indica que es HTML
            );

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new GlobalException("El email no se ha podido enviar correctamente.");
        }
    }
}
