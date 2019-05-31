package com.test;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class Beans {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
