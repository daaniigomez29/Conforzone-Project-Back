package com.project.conforzone.services.impl;

import com.project.conforzone.model.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final Map<String, RegisterRequest> cache = new ConcurrentHashMap<>();

    // Guardar temporalmente los datos del usuario antes de confirmar el registro
    public void storeUserData(String email, RegisterRequest request) {
        cache.put(email, request);
    }

    // Recuperar los datos del usuario cuando haga clic en el enlace
    public RegisterRequest getRegisterRequest(String email) {
        return cache.get(email);
    }

    // Eliminar los datos del usuario una vez registrado
    public void removeUserData(String email) {
        cache.remove(email);
    }
}
