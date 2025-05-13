package com.project.conforzone.util;

import java.util.regex.Pattern;

public class PatternEmail {
    public static boolean isValidEmail(String toEmail) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";  // Expresión regular para validar el formato
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(toEmail).matches();
    }
}
