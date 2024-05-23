package org.example.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        /*
                    Cette methode permet de configuer la chaine de filtre de spring
                    Pour protéger une route spring security associe un pattern à une règle
                    exemple :
                    .requestMatchers("/login").permitAll()

                    authorise toutes les requêtes vers la route /login même si l'utilisateur n'est pas connecté

                    .requestMatchers("/saisons/*").hasRole("ADMIN")

                    authorise seulement les utilisateurs avec le role ROLE_ADMIN
                    ----------------------
                    Il existe plusieurs façon de créer un pattern

                    - méthode hasRole("ROLE") // nécéssite le role ROLE
                    - méthode hasAnyRole("ROLE1", "ROLE2") // nécéssite le role ROLE1 ou ROLE2

                    - access(new WebExpressionAuthorizationManager("hasRole('ROLE1') AND hasRole('ROLE2')")) // nécéssite le role ROLE1 et ROLE2
                    // avec cette méthode il est possible d'écrire des patterns plus complexes avec le language SPEL (Spring Expression Language) doc (https://docs.spring.io/spring-security/reference/6.0/servlet/authorization/expression-based.html)
                    // ex "hasRole('admin') and hasIpAddress('192.168.1.0/24')"


                    ----------------------------------------

                    Attention l'ordre des règles a une importance :

                      .requestMatchers("/**").permitAll()
                      .requestMatchers("/dashboard/").hasRole("ADMIN")

                      n'a pas le même effet que:

                      .requestMatchers("/dashboard/").hasRole("ADMIN")
                      .requestMatchers("/**").permitAll()

                      Dans le premier cas la règle ".requestMatchers("/dashboard/").hasRole("ADMIN")"
                      ne sera jamais prise en compte car spring security s'arrête à la première règle
                      et ".requestMatchers("/**").permitAll()" match avec toutes les routes

                      dans le deuxième exemple la protection de la route "/dashboard" sera effective
         */

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        ).authorizeHttpRequests(authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers("/login").permitAll()
                                    .requestMatchers("/saisons/*").hasRole("ADMIN")
                                    .requestMatchers("/days/{id}").hasRole("ADMIN")
                                    .requestMatchers("/days/saison/{id}").hasAnyRole("ADMIN", "MODERATOR")
                                    .requestMatchers("/teams/{id}").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') AND hasRole('MODERATOR')"))

                                    .anyRequest().authenticated()
                        ).addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



        /*
            .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            ici on remplace le filtre par default de spring security par notre propre filtre customAuthenticationFilter
            cela nous permet de se connecter en fournissant au serveur les credentials via un objet json plustôt
            que par le formulaire par default. Cependant dans cette example il n'y a pas de persistance de la session.
         */

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
