package com.f1rst.desafio.consulta.cep.api.aspect;

import com.f1rst.desafio.consulta.cep.api.exception.CepFormatException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CepValidationAspect {

    @Before("execution(* com.f1rst.desafio.consulta.cep.api.service.ConsultaCepService.consultaCep(..)) && args(cep)")
    public void validateCepFormat(String cep) {
        if (!cep.matches("\\d{8}") && !cep.matches("\\d{5}-\\d{3}")) {
            throw new CepFormatException("CEP deve conter 8 dígitos ou 5 dígitos seguidos de hífen e mais 3 dígitos");
        }
    }

}
