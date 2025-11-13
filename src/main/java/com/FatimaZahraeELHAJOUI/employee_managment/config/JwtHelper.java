package com.FatimaZahraeELHAJOUI.employee_managment.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtHelper {
    
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        final Date tokenExpirationDate = extractClaim(token, Claims::getExpiration);
        boolean usernameMatch = Objects.equals(username, userDetails.getUsername());
        boolean tokenIsExpired = tokenExpirationDate.before(new Date(System.currentTimeMillis()));
        return usernameMatch && !tokenIsExpired;
    }

    public String generateToken(UserDetails userDetails) {
        return this.generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)  // Changed from .claims() to .setClaims()
                .setSubject(userDetails.getUsername())  // Changed from .subject() to .setSubject()
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Changed from .issuedAt()
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 14))  // Changed from .expiration()
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)  // Added SignatureAlgorithm
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()  // Changed from .parser()
                .setSigningKey(getSignInKey())  // Changed from .verifyWith()
                .build()
                .parseClaimsJws(token)  // Changed from .parseSignedClaims()
                .getBody();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}