package com.rest.webservices.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


// Create documentation using swagger
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // Custom documentation Infos
    private static final ApiInfo DEFAULT_API_INFO;
    private static final Contact DEFAULT_CONTACT = new Contact("Egon", "url", "email");
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList(
            "application/json", "application/xml"));

    static {
        DEFAULT_API_INFO = new ApiInfo("Api Documentation", "Api Documentation", "1.0",
                "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}
