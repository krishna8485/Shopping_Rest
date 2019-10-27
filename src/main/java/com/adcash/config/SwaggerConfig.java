package com.adcash.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Parameter;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	//TODO: Need to work on authorization part to work in swagger UI
        
    @Bean
    public Docket api() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization")                 
        				 .description("provide Authorization value as given user:password or admin:password")
                         .modelRef(new ModelRef("string"))
                         .parameterType("header")               
                         .defaultValue("user:password")        // based64 
                         .required(true)                
                         .build();
        java.util.List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());             // add parameter
        return new Docket(DocumentationType.SWAGGER_2).select()
        											  .apis(RequestHandlerSelectors.basePackage("com.adcash"))
        											  .paths(PathSelectors.any())
                										.build()
                										.pathMapping("")
                                                        .globalOperationParameters(aParameters);
        
    }



}
