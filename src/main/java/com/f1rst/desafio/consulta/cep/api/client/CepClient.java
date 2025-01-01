package com.f1rst.desafio.consulta.cep.api.client;

import com.f1rst.desafio.consulta.cep.api.client.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cepClient", url = "${api.integration.cep.url}")
public interface CepClient {

    @GetMapping("/cep/{cep}")
    Endereco consultaCep(@PathVariable("cep") String cep);

}
