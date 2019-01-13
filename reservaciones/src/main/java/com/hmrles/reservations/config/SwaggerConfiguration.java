package com.hmrles.reservations.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase de configuraci&oacute;n para Swagger
 * 
 * @author HMrles (hmorales@gmx.com)
 * @version 1.0
 * @since Enero 2019
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	public Docket documentation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build();
	}
}
