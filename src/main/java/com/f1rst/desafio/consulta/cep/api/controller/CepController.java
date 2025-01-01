package com.f1rst.desafio.consulta.cep.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.f1rst.desafio.consulta.cep.api.domain.dto.EnderecoResponse;
import com.f1rst.desafio.consulta.cep.api.domain.dto.ErrorDetails;
import com.f1rst.desafio.consulta.cep.api.service.ConsultaCepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/api/consulta-cep", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class CepController {

    private final ConsultaCepService consultaCepService;

    @GetMapping(path = "/{cep}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponse.class))}),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    @Operation(
            summary = "Permite consultar endereço pelo CEP",
            tags = {"CEP"}
    )
    public EnderecoResponse consulta(@PathVariable(name = "cep") String cep) {
        return consultaCepService.consulta(cep);
    }

}
