package com.f1rst.desafio.consulta.cep.api.controller;

import com.f1rst.desafio.consulta.cep.api.domain.dto.LogResponse;
import com.f1rst.desafio.consulta.cep.api.service.ConsultaLogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final ConsultaLogService consultaLogService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Permite consultar logs de consultas de CEP",
            tags = {"LOGS"}
    )
    public List<LogResponse> consulta() {
        return consultaLogService.consulta();
    }

}
