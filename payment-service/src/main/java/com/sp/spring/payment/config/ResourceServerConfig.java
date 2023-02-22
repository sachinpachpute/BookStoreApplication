package com.sp.spring.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class ResourceServerConfig {

    @Value("${spring.security.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(
                r -> r.jwt().jwkSetUri(issuerUri)
                        .jwtAuthenticationConverter(new CustomJwtAuthenticationTokenConverter())
        );
        http.authorizeHttpRequests()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/products","/payment/**", "/image/**").permitAll()
                /*.requestMatchers("/services").hasAuthority("SCOPE_services:read")*/
                .anyRequest().authenticated();
        return http.build();
    }
}
