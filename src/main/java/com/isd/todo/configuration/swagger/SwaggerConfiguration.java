package com.isd.todo.configuration.swagger;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Autowired
	private SecurityScheme securityScheme;

	@Autowired
	private SecurityContext securityContext;

	private final Predicate<String> TO_DO_TASKS_API = PathSelectors.ant("/secured/to-do-tasks/**")::apply;
	private final Predicate<String> OAUTH_API = PathSelectors.ant("/oauth/**")::apply;

	@Bean
	public Docket toDoTasksApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("1. To Do Tasks API")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(TO_DO_TASKS_API::test)
				.build()
				.securitySchemes(Lists.newArrayList(securityScheme))
				.securityContexts(Lists.newArrayList(securityContext));
	}

	@Bean
	public Docket authenticationApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("2. OAuth 2.0 API")
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(OAUTH_API::test)
				.build();
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("Victor Stelea", "https://github.com/victor-stelea", "victor.stelea@gmail.com");
		return new ApiInfoBuilder()
				.title("To Do Tasks App")
				.description("To Do Tasks REST API")
				.contact(contact)
				.build();
	}
}