package com.mini3.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserSwaggerApi 
{
	@Bean
	GroupedOpenApi UserApi()
	{
		return GroupedOpenApi
				.builder()
				.group("user-api")
				.packagesToScan("com.mini3.controller")
				.build();
	}
}