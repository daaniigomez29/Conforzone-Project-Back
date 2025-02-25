package com.project.conforzone;

import com.project.conforzone.model.dto.EmailModel;
import com.project.conforzone.services.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ConforzoneApplication {

	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(ConforzoneApplication.class, args);
	}

}
