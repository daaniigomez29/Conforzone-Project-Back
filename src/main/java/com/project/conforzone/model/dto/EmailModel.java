package com.project.conforzone.model.dto;

import com.project.conforzone.model.EmailSubject;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailModel {
    private String FromEmail;
    private EmailSubject subject;
    private String body;
}
