package com.f1rst.desafio.consulta.cep.api.service;

import com.f1rst.desafio.consulta.cep.api.domain.entity.ConsultaCepLog;
import com.f1rst.desafio.consulta.cep.api.repository.ConsultaCepLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalvaLogService {

    private final ConsultaCepLogRepository logRepository;

    public void salva(ConsultaCepLog log) {
        logRepository.save(log);
    }
}
