package com.f1rst.desafio.consulta.cep.api.service;

import com.f1rst.desafio.consulta.cep.api.client.CepClient;
import com.f1rst.desafio.consulta.cep.api.client.model.EnderecoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultaCepService {

    private final CepClient cepClient;

    public EnderecoResponse consulta(String cep) {
        return cepClient.consultaCep(cep);
    }

}
