package com.f1rst.desafio.consulta.cep.api.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnderecoResponse {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

}
