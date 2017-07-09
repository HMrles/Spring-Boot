package com.hmrles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class SpringMVC4AngularJSApplication 
	extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder 
	configure(SpringApplicationBuilder application) {
		return application.sources(SpringMVC4AngularJSApplication.class);
	}
	
	
    public static void main(String[] args) {
        SpringApplication.run(SpringMVC4AngularJSApplication.class, args);
    }
}
