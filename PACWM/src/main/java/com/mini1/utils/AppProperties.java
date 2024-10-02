package com.mini1.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class AppProperties {
	private Map<String, String> msg=new HashMap<>();
}
