package org.example.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.config.security.dto.CustomUserDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private final SecretKey secretKey;

    public JwtUtils(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)  {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(secretKey)
                .compact();
    }

    public String generateToken(CustomUserDetail userDetail) {
        Map<String, Object> claims = Map.of(
          "roles", userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
          "userId", userDetail.getId()
        );
        return createToken(claims, userDetail.getUsername());
    }

        public Boolean validateToken(String token)  {
            return !isTokenExpired(token);
        }

        public List<String> extractRoles(String token) {
            Claims claims = extractAllClaims(token);
            return claims.get("roles", List.class);
        }

        public List<GrantedAuthority> getAuthorities(List<String> roles) {
            return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }

        public Long extractUserId(String token) {
            Claims claims = extractAllClaims(token);
            return claims.get("userId", Long.class);
        }

}
