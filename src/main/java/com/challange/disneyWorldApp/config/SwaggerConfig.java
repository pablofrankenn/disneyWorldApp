

package com.challange.disneyWorldApp.config;

import java.util.Arrays;

// @author Franken

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.challange.disneyWorldApp")).paths(PathSelectors.any())
				.build().apiInfo(getApiInfo()).securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(authorizationBearer()));

	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Disney World App", "Characters and Movies from Disney", "1.0", "",
				new Contact("DisneyWorldApp", "", "pablo.frankenn@gmail.com"), "LICENSE", "LICENSE URL",
				Collections.emptyList());
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(Arrays.asList(new SecurityReference("apiKey",
				new AuthorizationScope[] { new AuthorizationScope("global", "Global access") }))).build();
	}

	private ApiKey authorizationBearer() {
		return new ApiKey("apiKey", "Authorization", "header");
	}
}

