package com.intcomcorp.intcomcorpApplication.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurationSupport {
	/**
	 * 
	 * @return
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.intcomcorp.intcomcorpApplication.controller"))
				.paths(PathSelectors.regex("/zabbixapi/.*")).build().apiInfo(apiEndPointsInfo())
				.securitySchemes(apiKey()).securityContexts(Collections.singletonList(securityContext()));

	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		
		
	}

	/**
	 * 
	 * @return
	 */
	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("activeARC REST API").description(
				"This Apis belongs to International Communications Corp ,PVT LTD. \n Created and Managed by Chetu Pvt Ltd.")
				.contact(new Contact("ICCN", "http://www.chetu.com", "iccn@chetu.com")).license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0").build();
	}

	/**
	 * 
	 * @return
	 */
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/zabbixapi/.*")).build();
	}

	/**
	 * 
	 * @return
	 */
	private List<SecurityReference> defaultAuth() {
		SecurityReference securityReference = SecurityReference.builder().reference("basicAuth")
				.scopes(new AuthorizationScope[0]).build();

		ArrayList<SecurityReference> reference = new ArrayList<>(1);
		reference.add(securityReference);

		ArrayList<SecurityContext> securityContexts = new ArrayList<>(1);
		securityContexts.add(SecurityContext.builder().securityReferences(reference).build());

		return Collections.singletonList(securityReference);
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<SecurityScheme> apiKey() {
		ArrayList<SecurityScheme> auth = new ArrayList<>(1);
		auth.add(new BasicAuth("basicAuth"));
		return auth;

	}

}
