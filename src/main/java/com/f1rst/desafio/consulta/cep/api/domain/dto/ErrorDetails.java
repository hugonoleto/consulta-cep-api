package com.f1rst.desafio.consulta.cep.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private int statusCode;
    private String message;

}
