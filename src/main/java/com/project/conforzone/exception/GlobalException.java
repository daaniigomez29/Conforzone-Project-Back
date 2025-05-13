package com.project.conforzone.exception;

/**
 * Clase que crea una excepción en base al mensaje que queramos
 */
public class GlobalException extends RuntimeException{

        public GlobalException(String exception){
            super(exception);
        }
}
