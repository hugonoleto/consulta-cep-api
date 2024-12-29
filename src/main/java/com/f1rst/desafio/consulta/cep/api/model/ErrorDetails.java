package com.f1rst.desafio.consulta.cep.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private int statusCode;
    private String message;

}
