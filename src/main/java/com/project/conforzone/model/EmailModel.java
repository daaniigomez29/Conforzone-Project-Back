package com.project.conforzone.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailModel {
    private String toEmail;
    private String subject;
    private String body;
}
