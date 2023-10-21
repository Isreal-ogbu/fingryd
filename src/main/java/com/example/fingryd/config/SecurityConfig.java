package com.example.fingryd.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuth;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf()
                .disable()
                .authorizeHttpRequests((authorization) -> authorization
                        .requestMatchers("/api/v1/a/**").hasAnyAuthority("ADMIN", "USER", "SUPERADMIN")
                        .requestMatchers("/api/v1/c/**").hasAnyAuthority("ADMIN", "USER", "SUPERADMIN")
                        .requestMatchers("/api/v1/e/**").hasAnyAuthority("ADMIN", "USER", "SUPERADMIN")
                        .requestMatchers("/api/v1/t/**").hasAnyAuthority("ADMIN", "USER", "SUPERADMIN")
                        .requestMatchers("/api/v1/fin/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .anyRequest().authenticated());
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuth, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
