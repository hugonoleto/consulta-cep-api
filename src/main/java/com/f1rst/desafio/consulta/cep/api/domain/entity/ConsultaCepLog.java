package com.f1rst.desafio.consulta.cep.api.domain.entity;

import com.f1rst.desafio.consulta.cep.api.domain.TipoLog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "consulta_cep_log")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaCepLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    @JdbcTypeCode(SqlTypes.JSON)
    private Object response;

    private Integer statusCode;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TipoLog tipo;

    private String correlationId;

}