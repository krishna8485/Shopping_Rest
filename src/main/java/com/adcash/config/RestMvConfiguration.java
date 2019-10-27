package com.adcash.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestMvConfiguration implements WebMvcConfigurer{
 
	  @Override
	    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	        configurer.favorPathExtension(false).
	        favorParameter(true).
	        parameterName("mediaType").
	        ignoreAcceptHeader(false).
	        defaultContentType(MediaType.APPLICATION_JSON).
	        mediaType("xml", MediaType.APPLICATION_XML). 
	        mediaType("json", MediaType.APPLICATION_JSON); 
	    }
}
