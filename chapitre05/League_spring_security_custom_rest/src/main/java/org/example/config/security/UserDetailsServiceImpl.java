package org.example.config.security;

import org.example.config.security.dto.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*

        Spring utilise cette methode pour récupérer le mot de passe de l'utilisateur en base de donnée
        et le comparer avec le mot de passe entré par l'utilisateur.
        Dans cet example les informations de l'utilisateur, ici le mot de passe et l'id, ne sont pas récupérées en base de donnée mais
        enregistrée en dur dans le code  =>     String encodedPassword = passwordEncoder.encode("pass");


        Un UserDetails est fournit en retour.

        Il est possible de spécifier des informations concernant l'utilisateur authentifié username, roles, id

        Il est aussi possible d'indiquer au module de sécurité si l'utilisateur est activé et
         si il est bloqué (voir le constructeur de CustomUserDetail

       public CustomUserDetail(String username, String password, Long id, boolean enabled,
    boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super (username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
       this.id = id;
    }

    Les roles passé en paramètre de CustomUserDetail sont utilisés par la chaine de validation de spring security pour
    matcher les roles de l'utilisateur avec les routes.
         */

        String encodedPassword = passwordEncoder.encode("pass");

        List<String> roles = List.of("ROLE_MODERATOR", "ROLE_ADMIN");
        return new CustomUserDetail(username, encodedPassword, 1L, true, true, true, true,
                roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()
                ));

    }


}
