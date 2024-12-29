package com.f1rst.desafio.consulta.cep.api.client;

import com.f1rst.desafio.consulta.cep.api.client.model.EnderecoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cepClient", url = "http://localhost:8081")
public interface CepClient {

    @GetMapping("/cep/{cep}")
    EnderecoResponse consultaCep(@PathVariable("cep") String cep);

}
