package com.f1rst.desafio.consulta.cep.api.mapper;

import com.f1rst.desafio.consulta.cep.api.client.model.Endereco;
import com.f1rst.desafio.consulta.cep.api.domain.dto.EnderecoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {

    EnderecoMapper INSTANCIA = Mappers.getMapper(EnderecoMapper.class);

    EnderecoResponse paraEnderecoResponse(Endereco endereco);

}
