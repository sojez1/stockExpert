package com.jpstechno.stock_back.securites;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final MyAuthenticationProvider myAuthenticationProvider;
    private final JwtFilter jwtFilter;

    public SecurityConfig(MyAuthenticationProvider myAuthenticationProvider, JwtFilter jwtFilter) {
        this.myAuthenticationProvider = myAuthenticationProvider;
        this.jwtFilter = jwtFilter;

    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(myAuthenticationProvider)
                .authorizeHttpRequests(authReq -> authReq
                        .requestMatchers(SecurityParams.publicEndpoints).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

}
