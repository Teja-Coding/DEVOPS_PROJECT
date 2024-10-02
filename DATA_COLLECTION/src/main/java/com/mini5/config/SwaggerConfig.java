package com.mini5.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig 
{
	@Bean
	GroupedOpenApi controllerApi()
	{
		return GroupedOpenApi
				.builder()
				.group("data-collection")
				.packagesToScan("com.mini5.controller")
				.build();
	}
}
