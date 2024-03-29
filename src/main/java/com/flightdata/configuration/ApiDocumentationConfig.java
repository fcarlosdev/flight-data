package com.flightdata.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentationConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Flight Management Data")
                        .description("Flight API for management flights data ")
                        .version("0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation")
                        .url("https:/wiki...."));
    }

}
