package com.isd.todo.configuration.security.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.util.List;

@Configuration
public class OAuthSwaggerSecurityConfiguration {

    private static final String SECURITY_SCHEME_NAME = "spring_oauth";
    private static final String CLIENT_ID = "spring-security-oauth2-read-write-client";
    private static final String CLIENT_SECRET = "spring-security-oauth2-read-write-client-password1234";

    @Bean
    public SecurityConfiguration securityInfo() {
        return SecurityConfigurationBuilder.builder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .scopeSeparator(" ")
                .build();
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations")
        };
    }

    private List<SecurityReference> securityReferences() {
        return Lists.newArrayList(new SecurityReference(SECURITY_SCHEME_NAME, scopes()));
    }

    @Bean
    public SecurityScheme securityScheme() {
        LoginEndpoint loginEndpoint = new LoginEndpoint("http://localhost:9080/oauth/authorize");
        GrantType grantType = new ImplicitGrant(loginEndpoint, OAuth2AccessToken.ACCESS_TOKEN);

        return new OAuthBuilder().name(SECURITY_SCHEME_NAME)
                .grantTypes(Lists.newArrayList(grantType))
                .scopes(Lists.newArrayList(scopes()))
                .build();
    }

    @Bean
    public SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(securityReferences())
                .forPaths(s -> true)
                .build();
    }
}
