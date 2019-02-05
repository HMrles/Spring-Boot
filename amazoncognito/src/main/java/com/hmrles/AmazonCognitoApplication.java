package com.hmrles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * <h4>AmazonCognitoApplication</h4>
 * <p>
 * Clase inicializadora del proyecto AmazonCognito
 * </p>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */

@SpringBootApplication
public class AmazonCognitoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AmazonCognitoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AmazonCognitoApplication.class);
	}
}
