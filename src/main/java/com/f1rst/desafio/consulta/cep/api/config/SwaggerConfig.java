package com.f1rst.desafio.consulta.cep.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  public static final String SWAGGER_DESCRICAO = "API responsável pela consulta de CEP e registro de logs";
  public static final String SWAGGER_TITULO = "Consulta CEP API";
  public static final String SWAGGER_VERSAO = "1.0.0";

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
        .info(new Info()
            .title(SWAGGER_TITULO)
            .description(SWAGGER_DESCRICAO)
            .version(SWAGGER_VERSAO));
  }

  @Bean
  public GroupedOpenApi groupOpenApi() {
    String[] paths = {"/api/**"};
    String[] packagesToscan = {"com.f1rst.desafio.consulta.cep.api"};
    return GroupedOpenApi.builder().group("v1").pathsToMatch(paths).packagesToScan(packagesToscan)
        .build();
  }
}