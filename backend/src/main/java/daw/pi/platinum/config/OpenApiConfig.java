package daw.pi.platinum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
    
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Platinum API")
                        .version("1.0")
                        .description("Platinum public API documentation. Platinum is an Open Source forum focused comunitiy driven website focused on giving users detailed information about Steam games and achievements.")
                        .contact(new Contact()
                                .name("Erik Vives von Heyne")
                                .email("erikvvh06@gmail.com")))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Please put your JWT generated token here")));
    }
}

