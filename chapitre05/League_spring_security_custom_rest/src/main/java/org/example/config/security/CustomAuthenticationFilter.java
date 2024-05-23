package org.example.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.config.security.dto.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("----------------");
        logger.debug("----------------");
        try {
            System.out.println(request);
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            /*
                Cette methode remplace le formulaire par default et permet d'intercepter la requête http
                ce qui permet d'extraire l'username et le mot de passe de l'utilisateur afin de procéder à
                l'authentication qui à lieu dans le service UserDetailsServiceImpl

             */
            System.out.println(loginRequest.getUsername());
            System.out.println(loginRequest.getPassword());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
              loginRequest.getUsername(),
              loginRequest.getPassword()
            );

            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
/*
    En surchargeant la  methode  successfulAuthentication il est possible d'executer un code personnalisé
    lorsque l'authentification à réussie. Dans ce cas on envoie un message "success authentication"
    et le code de retour 200.
    Dans un cas plus complexe avec gestion de la persistance de la session il faudrait retourner à l'utilisateur
    un cookie de session signé par exemple en utilisant jwt.

 */


    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        logger.debug("successfulAuthentication method called");

        response.getWriter().write("success authentication");
        response.setStatus(HttpServletResponse.SC_OK);

    }
/*
    En surchargeant la  methode  unsuccessfulAuthentication il est possible d'executer un code personnalisé
    lorsque l'authentification à échouée.
     Dans ce cas on envoie un message ""unsuccessful authentication"
    et le code de retour 401.


 */


    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        logger.debug("unsuccessfulAuthentication method called");
        response.getWriter().write("unsuccessful authentication");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
