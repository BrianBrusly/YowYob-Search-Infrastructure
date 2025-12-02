/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
 *  org.springframework.security.config.web.server.ServerHttpSecurity
 *  org.springframework.security.config.web.server.ServerHttpSecurity$AuthorizeExchangeSpec$Access
 *  org.springframework.security.web.server.SecurityWebFilterChain
 *  org.springframework.web.cors.CorsConfiguration
 *  org.springframework.web.cors.reactive.CorsConfigurationSource
 *  org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
 */
package com.example.template.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.cors(cors -> cors.configurationSource(this.corsConfigurationSource())).csrf(csrf -> csrf.disable()).authorizeExchange(exchanges -> ((ServerHttpSecurity.AuthorizeExchangeSpec.Access)((ServerHttpSecurity.AuthorizeExchangeSpec.Access)((ServerHttpSecurity.AuthorizeExchangeSpec.Access)exchanges.pathMatchers(new String[]{"/management/**"})).permitAll().pathMatchers(new String[]{"/swagger-ui.html", "/swagger-ui/**", "/api-docs/**", "/webjars/**"})).permitAll().pathMatchers(new String[]{"/", "/api/users/**", "/api/users/**", "/tmp/tempo/traces"})).permitAll().anyExchange().authenticated()).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(Boolean.valueOf(true));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
