package com.f1rst.desafio.consulta.cep.api.controller;

import com.f1rst.desafio.consulta.cep.api.client.model.EnderecoResponse;
import com.f1rst.desafio.consulta.cep.api.service.ConsultaCepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/consulta-cep", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class ConsultaCepController {

    private final ConsultaCepService consultaCepService;

    @GetMapping(path = "/{cep}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Permite consultar endereço pelo CEP",
            tags = {"cep"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "CEP inválido", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content(mediaType = "application/json"))
            }
    )
    public EnderecoResponse consultaCep(@PathVariable(name = "cep") String cep) {
        return consultaCepService.consultaCep(cep);
    }

}
