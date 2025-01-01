package com.f1rst.desafio.consulta.cep.api.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> converteParaMap(Object response) {
        if (response == null) {
            return null;
        }
        try {
            if (response instanceof InputStream) {
                return objectMapper.readValue((InputStream) response, new TypeReference<>() {});
            } else {
                return objectMapper.readValue(response.toString(), new TypeReference<>() {});
            }

        } catch (IOException e) {

            return null;
        }
    }
}
