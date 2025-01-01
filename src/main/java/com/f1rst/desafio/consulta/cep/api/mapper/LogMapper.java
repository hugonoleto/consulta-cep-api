package com.f1rst.desafio.consulta.cep.api.mapper;

import com.f1rst.desafio.consulta.cep.api.domain.dto.LogResponse;
import com.f1rst.desafio.consulta.cep.api.domain.entity.ConsultaCepLog;
import com.f1rst.desafio.consulta.cep.api.util.JsonUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = JsonUtils.class)
public interface LogMapper {

    LogMapper INSTANCIA = Mappers.getMapper(LogMapper.class);

    List<LogResponse> paraLogsResponse(List<ConsultaCepLog> entities);

    @Mapping(target = "response", expression = "java(JsonUtils.converteParaMap(entity.getResponse()))")
    LogResponse paraLogResponse(ConsultaCepLog entity);

}
