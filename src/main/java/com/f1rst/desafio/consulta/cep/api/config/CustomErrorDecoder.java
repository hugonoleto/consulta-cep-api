package com.f1rst.desafio.consulta.cep.api.config;

import com.f1rst.desafio.consulta.cep.api.util.JsonUtils;
import com.f1rst.desafio.consulta.cep.api.exception.CepNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Map;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            try {
                Map<String, Object> errorMap = JsonUtils.converteParaMap(response.body().asInputStream());
                String errorMessage = (String) errorMap.get("message");
                return new CepNotFoundException(errorMessage);
            } catch (IOException e) {
                return new CepNotFoundException("Endereço não encontrado");
            }
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}