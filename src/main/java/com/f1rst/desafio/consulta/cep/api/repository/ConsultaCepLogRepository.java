package com.f1rst.desafio.consulta.cep.api.repository;

import com.f1rst.desafio.consulta.cep.api.domain.entity.ConsultaCepLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaCepLogRepository extends JpaRepository<ConsultaCepLog, Long> {
}
