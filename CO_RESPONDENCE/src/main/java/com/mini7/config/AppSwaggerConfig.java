package com.mini7.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppSwaggerConfig 
{
	@Bean
	GroupedOpenApi edControllerApi()
	{
		return GroupedOpenApi
				.builder()
				.group("correspondence")
				.packagesToScan("com.mini7.controller")
				.build();
	}
}
