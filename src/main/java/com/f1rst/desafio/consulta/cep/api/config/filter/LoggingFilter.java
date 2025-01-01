package com.f1rst.desafio.consulta.cep.api.config.filter;

import static com.f1rst.desafio.consulta.cep.api.domain.TipoLog.REQUEST;
import static com.f1rst.desafio.consulta.cep.api.domain.TipoLog.RESPONSE;

import com.f1rst.desafio.consulta.cep.api.domain.TipoLog;
import com.f1rst.desafio.consulta.cep.api.domain.entity.ConsultaCepLog;
import com.f1rst.desafio.consulta.cep.api.repository.ConsultaCepLogRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingFilter implements Filter {

    private final ConsultaCepLogRepository consultaCepLogRepository;

    private static final String ENDPOINT_CEP_PADRAO = "^/api/consulta-cep/.+";
    private static final String EMPTY_RESPONSE = "{}";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isEndpointConsultaCep(httpRequest.getRequestURI())) {
            processaEndpointConsultaCep(httpRequest, httpResponse, chain);
        } else {
            chain.doFilter(request, response);
        }
    }

    private void processaEndpointConsultaCep(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                             FilterChain chain) throws IOException, ServletException {
        LocalDateTime dataHoraRequest = LocalDateTime.now();

        CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(httpResponse);

        try {
            chain.doFilter(httpRequest, responseWrapper);
        } finally {
            processaGravacaoLog(httpRequest, responseWrapper, dataHoraRequest);
            responseWrapper.copyBodyToResponse();
        }
    }

    private void processaGravacaoLog(HttpServletRequest httpRequest,
                                   CustomHttpServletResponseWrapper responseWrapper, LocalDateTime dataHoraRequest) {
        String correlationId = UUID.randomUUID().toString();
        getCep(httpRequest).ifPresent(cep -> {
            try {
                logRequest(correlationId, cep, dataHoraRequest);
                logResponse(responseWrapper, correlationId, cep);
            } catch (IOException e) {
                log.error("[LoggingFilter] Erro ao logar da requisição do cep {}", cep, e);
            }
        });
    }

    private Optional<String> getCep(HttpServletRequest request) {
        return Optional.ofNullable((Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE))
                .map(pathVariables -> pathVariables.get("cep"));
    }

    private void logRequest(String correlationId, String cep, LocalDateTime dataHoraRequest) {
        salvaLog(correlationId, cep, dataHoraRequest, REQUEST, null, null);
    }

    private void logResponse(CustomHttpServletResponseWrapper responseWrapper,
                             String correlationId, String cep) throws IOException {
        String responseContent = responseWrapper.getCaptureAsString();
        int statusCode = responseWrapper.getStatus();

        if (responseContent.isEmpty()) {
            responseContent = EMPTY_RESPONSE;
        }

        salvaLog(correlationId, cep, LocalDateTime.now(), RESPONSE, responseContent, statusCode);
    }

    private void salvaLog(String correlationId, String cep, LocalDateTime dataHora,
                         TipoLog tipo, String responseContent, Integer statusCode) {
        ConsultaCepLog log = ConsultaCepLog.builder()
                .cep(cep)
                .dataHora(dataHora)
                .correlationId(correlationId)
                .tipo(tipo)
                .statusCode(statusCode)
                .response(responseContent)
                .build();

        consultaCepLogRepository.save(log);
    }

    private boolean isEndpointConsultaCep(String requestURI) {
        return Pattern.matches(ENDPOINT_CEP_PADRAO, requestURI);
    }
}