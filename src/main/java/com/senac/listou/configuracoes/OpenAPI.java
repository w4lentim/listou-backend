package com.senac.listou.configuracoes;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Hidden
@Configuration
@RestController
public class OpenAPI {

    @GetMapping(value = "/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/index.html");
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI springShopOpenAPI() {
        String securitySchemeName = "bearerAuth";
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info()
                        .title("Aplicação Listou - API")
                        .description("API para realização de requisições backend do app listou")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Grupo 14 - Senac EAD"))
                        .contact(new Contact()
                                .name("Github da API")
                                .url("https://github.com/w4lentim/listou-backend")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Clique aqui para visualizar o diagrama de banco de dados")
                        .url("LINK"));
    }
}
