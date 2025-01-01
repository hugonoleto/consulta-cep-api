package com.f1rst.desafio.consulta.cep.api.client.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Endereco {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

}
