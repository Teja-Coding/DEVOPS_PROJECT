package com.mini1.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlanSwaggerConfig 
{
	@Bean
	GroupedOpenApi controllerApi()
	{
		return GroupedOpenApi
				.builder()
				.group("plan-api")
				.packagesToScan("com.mini1.controller")
				.build();
	}
}