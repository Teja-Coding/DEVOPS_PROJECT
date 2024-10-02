package com.mini2.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportsSwaggerConfig 
{
	@Bean
	GroupedOpenApi ReportsApi()
	{
		return GroupedOpenApi
					.builder()
					.group("reports-api")
					.packagesToScan("com.mini2.controller")
					.build();
	}
}