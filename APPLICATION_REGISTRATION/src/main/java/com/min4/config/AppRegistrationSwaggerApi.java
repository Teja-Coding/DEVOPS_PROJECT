package com.min4.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppRegistrationSwaggerApi 
{
	@Bean
	GroupedOpenApi AppRegistraionApi() 
	{
		return GroupedOpenApi
				.builder().group("appRegistraion-api")
				.packagesToScan("com.min4.controller")
				.build();
	}
}
