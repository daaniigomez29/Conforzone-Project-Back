package com.project.conforzone.model;

import lombok.Getter;

@Getter
public enum EmailSubject {
    CUSTOMER_SERVICE("Atención al cliente"),
    REQUEST_QUOTE("Solicitar presupuesto");

    private final String label;

    EmailSubject(String label) {
        this.label = label;
    }
}
