package edu.cynanthus.auri.server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String jwtSecret;
    private final long jwtExpirationMilliseconds;

    @Autowired
    public JwtTokenProvider(
        @Value("${app.jwt-secret:ClaveSecrete}") String jwtSecret,
        @Value("${app.jwt-expiration-milliseconds:604800000}") long jwtExpirationMilliseconds
    ) {
        this.jwtSecret = jwtSecret;
        this.jwtExpirationMilliseconds = jwtExpirationMilliseconds;
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();
        Date endDate = new Date(currentDate.getTime() + jwtExpirationMilliseconds);

        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(endDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String getUsernameOf(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        return true;
    }

}
