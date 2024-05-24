package org.example.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    public SecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager manager) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(jwtUtils, manager);
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter(jwtUtils);

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        ).authorizeHttpRequests(authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers("/app.js").permitAll()
                                    .requestMatchers("/styles.css").permitAll()
                                    .requestMatchers("/index.html").permitAll()
                                    .requestMatchers("/login.html").permitAll()
                                    .requestMatchers("/register.html").permitAll()
                                    .requestMatchers("/saison.html").permitAll()
                                    .requestMatchers("/login").permitAll()
                                    .requestMatchers("/user").permitAll()
                                    .requestMatchers("/saisons/register/saison/{saidonId}/team/{teamId}").permitAll()
                                    .requestMatchers(HttpMethod.POST ,"/days").hasRole("MEMBER-LEAGUE")
                                    .requestMatchers(HttpMethod.POST, "/saisons").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.GET, "/saisons").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.POST, "/clashs").hasRole("JOURNALISTE")
                                    .requestMatchers(HttpMethod.POST ,"/teams*").permitAll()
                                    .requestMatchers("/days/{id}").permitAll()
                                    .requestMatchers("/days/saison/{id}").hasRole("ADMIN")
                                    .anyRequest().authenticated()
                        ).addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
