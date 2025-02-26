package com.project.conforzone.controller.impl;

import com.project.conforzone.controller.GlobalExceptionHandler;
import com.project.conforzone.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandlerImpl implements GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    @Override
    public ResponseEntity<Map<String, String>> handleGlobalException(GlobalException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
