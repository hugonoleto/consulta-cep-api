package com.f1rst.desafio.consulta.cep.api.service;

import com.f1rst.desafio.consulta.cep.api.client.CepClient;
import com.f1rst.desafio.consulta.cep.api.client.model.Endereco;
import com.f1rst.desafio.consulta.cep.api.domain.dto.EnderecoResponse;
import com.f1rst.desafio.consulta.cep.api.mapper.EnderecoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultaCepService {

    private final CepClient cepClient;

    public EnderecoResponse consulta(String cep) {
        Endereco endereco = cepClient.consultaCep(cep);

        return EnderecoMapper.INSTANCIA.paraEnderecoResponse(endereco);
    }

}
