package com.fmgarcia.service.management.files.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class FileExceptionHandler {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        
        // Aquí puedes personalizar el mensaje y el código de respuesta HTTP.
        // Se utiliza HttpStatus.PAYLOAD_TOO_LARGE (413) que es apropiado para este error.
        
        String mensajePersonalizado = "El archivo excede el tamaño máximo permitido. Por favor, sube un archivo más pequeño.";
        
        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE) // Código HTTP 413
                .body(mensajePersonalizado);
    }

}
