package com.f1rst.desafio.consulta.cep.api.exception;

public class CepNotFoundException extends RuntimeException {

    public CepNotFoundException(String message) {
        super(message);
    }

}
