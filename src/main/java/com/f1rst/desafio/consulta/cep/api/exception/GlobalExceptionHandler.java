package com.f1rst.desafio.consulta.cep.api.exception;

import com.f1rst.desafio.consulta.cep.api.domain.dto.ErrorDetails;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CepFormatException.class)
    public ResponseEntity<?> handleCepFormatException(CepFormatException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<?> handleCepNotFoundException(CepNotFoundException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleRetryableException(FeignException feignException, WebRequest webRequest){
        return buildErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, "Serviço indisponível");
    }

    private ResponseEntity<ErrorDetails> buildErrorResponse(HttpStatus status, String message) {
        ErrorDetails errorDetails = new ErrorDetails(status.value(), message);
        return new ResponseEntity<>(errorDetails, status);
    }
}