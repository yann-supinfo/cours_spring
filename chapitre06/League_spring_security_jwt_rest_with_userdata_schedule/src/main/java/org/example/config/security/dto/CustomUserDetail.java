package org.example.config.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetail extends User {

    private Long id;

    public CustomUserDetail(String username, String password, Long id, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.id = id;
    }

    public CustomUserDetail(String username, String password, Long id, boolean enabled,
    boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super (username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
       this.id = id;
    }

    public Long getId() {
        return id;
    }

}
