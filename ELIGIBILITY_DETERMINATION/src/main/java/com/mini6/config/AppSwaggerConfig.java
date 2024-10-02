package com.mini6.config;

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
				.group("eligiilityDetermination")
				.packagesToScan("com.mini6.controller")
				.build();
	}
}
