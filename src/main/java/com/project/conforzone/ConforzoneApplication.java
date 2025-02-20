package com.project.conforzone;

import com.project.conforzone.model.EmailModel;
import com.project.conforzone.services.impl.EmailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ConforzoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConforzoneApplication.class, args);
	}

}
