package com.f1rst.desafio.consulta.cep.api.service;

import com.f1rst.desafio.consulta.cep.api.domain.dto.LogResponse;
import com.f1rst.desafio.consulta.cep.api.domain.entity.ConsultaCepLog;
import com.f1rst.desafio.consulta.cep.api.mapper.LogMapper;
import com.f1rst.desafio.consulta.cep.api.repository.ConsultaCepLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaLogService {

    private final ConsultaCepLogRepository consultaCepLogRepository;

    public List<LogResponse> consulta() {
        List<ConsultaCepLog> logs = consultaCepLogRepository.findAll();

        return LogMapper.INSTANCIA.paraLogsResponse(logs);
    }
}