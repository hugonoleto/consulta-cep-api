package com.f1rst.desafio.consulta.cep.api.aspect;

import com.f1rst.desafio.consulta.cep.api.exception.CepFormatException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CepValidationAspect {

    private static final String PADRAO_CEP_8_DIGITOS = "\\d{8}";
    private static final String PADRAO_CEP_5_DIGITOS_HIFEN_3_DIGITOS = "\\d{5}-\\d{3}";

    @Before("execution(* com.f1rst.desafio.consulta.cep.api.service.ConsultaCepService.consulta(..)) && args(cep)")
    public void valida(String cep) {
        if (!cep.matches(PADRAO_CEP_8_DIGITOS) && !cep.matches(PADRAO_CEP_5_DIGITOS_HIFEN_3_DIGITOS)) {
            throw new CepFormatException("CEP deve conter 8 dígitos numéricos ou 5 dígitos numéricos " +
                    "seguidos de hífen e mais 3 dígitos numéricos");
        }
    }

}
