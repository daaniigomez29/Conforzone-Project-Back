package com.project.conforzone;

import com.project.conforzone.model.dto.EmailModel;
import com.project.conforzone.model.dto.SpecificServiceModelDto;
import com.project.conforzone.repository.SpecificServiceRepository;
import com.project.conforzone.services.EmailSenderService;
import com.project.conforzone.util.Mapper;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ConforzoneApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConforzoneApplication.class, args);
	}

}
