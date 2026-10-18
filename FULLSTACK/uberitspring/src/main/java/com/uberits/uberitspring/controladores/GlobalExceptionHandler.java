package com.uberits.uberitspring.controladores;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail manejarValidacion(MethodArgumentNotValidException ex) {
        String errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ProblemDetail detalle = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                errores.isBlank() ? "Los datos enviados no son válidos." : errores
        );
        detalle.setTitle("Error de validación");
        return detalle;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail manejarPeticionIncorrecta(
            HttpMessageNotReadableException ex) {
        ProblemDetail detalle = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "El cuerpo de la petición no tiene un formato válido."
        );
        detalle.setTitle("Petición incorrecta");
        return detalle;
    }
}
