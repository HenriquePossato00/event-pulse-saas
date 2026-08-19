package com.github.henriquepossato.eventpulse.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI eventPulseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EventPulse API")
                        .description("API de gestão e despacho de Webhooks e Eventos Assíncronos (SaaS Multi-tenant).")
                        .version("1.0.0"))
                .components(new Components()
                        // Define a segurança via Token JWT (Para o uso do Dashboard)
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token JWT fornecido pelo AWS Cognito após o login."))
                        // Define a segurança via Chave de API (Para a integração de sistemas)
                        .addSecuritySchemes("ApiKeyAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("x-api-key")
                                .description("Chave de API gerada no dashboard para integração.")));
    }
}