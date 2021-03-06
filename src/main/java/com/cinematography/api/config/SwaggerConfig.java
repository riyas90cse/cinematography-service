package com.cinematography.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;


/**
 * Swagger Configuration Configuration
 * @author Mohamed Riyas
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * @return docket
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cinematography"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    /**
     * @return api key
     */
    @Bean
    public ApiKey apiKey() { return new ApiKey("Bearer", "Authorization", "header"); }

    /**
     * Meta Data Method
     * @return apiinfo
     */
    public ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Cinematography REST API")
                .description("API Specification For Cinematography Application")
                .contact(new Contact("Mohamed Riyas", "https://example.com", "riyas90cse@gmail.com"))
                .license("Open Source")
                .licenseUrl(null)
                .version("1.0.0")
                .build();
    }

    /**
     * @return Security Context
     */
    public SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * To define the Defaulth Authentication
     * @return List of Security Reference
     */
    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope =
                new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
    }

}
