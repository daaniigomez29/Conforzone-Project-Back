package com.project.conforzone.util;


import jakarta.persistence.AttributeConverter;

public class MoneyConverter implements AttributeConverter<Integer, Double> {
    @Override
    public Double convertToDatabaseColumn(Integer cents) {
        return cents == null ? null : cents / 100.0;
    }

    @Override
    public Integer convertToEntityAttribute(Double euros) {
        return euros == null ? null : (int) (euros * 100);
    }
}
