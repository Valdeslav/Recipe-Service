package com.valdeslav.recipe.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;

@Service
@Slf4j
public class JwtTokenService {
    private final SecretKey jwtTokenSecretKey;

    public JwtTokenService(@Value("${token.jwt.key}") String jwtTokenSecret) {
        this.jwtTokenSecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtTokenSecret));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(jwtTokenSecretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("Token expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported jwt: " + e.getMessage());
        } catch (MalformedJwtException e) {
            log.info("Malformed jwt: " + e.getMessage());
        } catch (Exception e) {
            log.info("invalid token: " + e.getMessage());
        }

        return false;
    }

    public UserDetails extractUserDetails(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(jwtTokenSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return new JwtUserDetails(claims.getSubject(), claims.get("roles", List.class));
    }
}
