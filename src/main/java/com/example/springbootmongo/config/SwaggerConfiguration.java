package com.example.springbootmongo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String PATH_MAPPING = "/";

    private static final String PACKAGE_BASE = "com.example.springbootmongo";

    @Value("${info.version}")
    private String projectVersion;

    @Value("${info.name}")
    private String projectName;

    @Value("${info.description}")
    private String projectDescription;

    @Bean
    Docket rsApi() {
        return new Docket(DocumentationType.SWAGGER_12)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(PACKAGE_BASE))
            .paths(PathSelectors.any())
            .build()
            .pathMapping(PATH_MAPPING)
            .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(projectName)
            .description(projectDescription)
            .version(projectVersion)
            .build();
    }

}