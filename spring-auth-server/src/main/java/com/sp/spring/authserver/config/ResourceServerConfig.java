package com.sp.spring.authserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class ResourceServerConfig {

    @Value("${spring.security.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    @Order(2)
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(
                r -> r.jwt().jwkSetUri(issuerUri)
                        .jwtAuthenticationConverter(new CustomJwtAuthenticationTokenConverter())
        );
        http
                .formLogin()
                .loginPage("/login")
                //.permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID", "JWT")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .and().authorizeHttpRequests()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/userInfo").permitAll()
                /*.requestMatchers("/services").hasAuthority("SCOPE_services:read")*/
                .anyRequest().authenticated();
        return http.build();
    }

    /*    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ...
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/homepage.html", true)
                .failureUrl("/login.html?error=true")
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler());
        return http.build();

        loginPage() – the custom login page
        loginProcessingUrl() – the URL to submit the username and password to
        defaultSuccessUrl() – the landing page after a successful login
        failureUrl() – the landing page after an unsuccessful login
        logoutUrl() – the custom logout
    }*/
}
