package com.project.conforzone.controller;

import com.project.conforzone.exception.GlobalException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface GlobalExceptionHandler {
    public ResponseEntity<Map<String, String>> handleGlobalException(GlobalException ex);
    }
