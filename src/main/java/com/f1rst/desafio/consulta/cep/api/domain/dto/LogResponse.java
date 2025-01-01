package com.f1rst.desafio.consulta.cep.api.domain.dto;

import com.f1rst.desafio.consulta.cep.api.domain.TipoLog;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogResponse {

    private Long id;
    private String cep;
    private Map<String, Object> response;
    private Integer statusCode;
    private LocalDateTime dataHora;
    private TipoLog tipo;
    private String correlationId;

}
