package com.java.spatial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpatialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpatialApplication.class, args);
		System.out.println("Run");
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	RestOperations rest(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.basicAuthentication("admin", "geoserver").build();
	}

}
