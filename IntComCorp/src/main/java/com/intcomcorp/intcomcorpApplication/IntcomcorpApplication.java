package com.intcomcorp.intcomcorpApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication

@PropertySource({ "classpath:application-${spring.profiles.active}.properties", "classpath:db.properties" })
public class IntcomcorpApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(IntcomcorpApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(IntcomcorpApplication.class);
	}
}