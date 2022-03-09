package com.personal.school.config.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/actuator").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/actuator/*").hasRole("ADMIN")
            .anyRequest()
                .authenticated()
            .and()
                .oauth2ResourceServer()
                    .jwt()
                        .jwtAuthenticationConverter(getJwtAuthenticationConverter());
    }

    private JwtAuthenticationConverter getJwtAuthenticationConverter() {
        // Utilizar o campo "authorities" para receber as ROLEs e não o campo "scope", default do Spring
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("authorities");
        converter.setAuthorityPrefix(EMPTY);

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
        return authenticationConverter;
    }
}
