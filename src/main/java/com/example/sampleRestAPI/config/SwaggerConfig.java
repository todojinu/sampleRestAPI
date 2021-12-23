package com.example.sampleRestAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    // http://localhost:8080/swagger-ui/ - 접속경로

   @Bean
    public Docket api() {  // Docket 은 Swagger 설정의 핵심이 되는 Bean
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)  //Swagger 에서 제공해주는 기본 응답코드(200, 401, 403, 404). false 로 설정하면 기본응답코드 노출하지 않음
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.sampleRestAPI.controller"))  // API 스펙이 작성되어 있는 패키지(Controller) 지정
                .paths(PathSelectors.any())  //APIs 중 특정 path를 선택
                .build()
                .apiInfo(apiInfo())  // Swagger UI로 노출할 정보 설정
                ;
    }

    private ApiInfo apiInfo() {
       return new ApiInfoBuilder()
               .title("REST API Sample")
               .description("Sample REST APIs for practice")
               .version("0.1")
               .build();
    }

}
