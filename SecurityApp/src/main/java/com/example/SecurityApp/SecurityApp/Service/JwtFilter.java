package com.example.SecurityApp.SecurityApp.Service;

import com.example.SecurityApp.SecurityApp.Entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtFilter {
    private final String secretKey = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";
    long expirationTime = 1000 * 60 * 60;
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getToken(UserEntity userEntity){
        return Jwts.builder()
                .setSubject(userEntity.getEmail())
                .claim("id", userEntity.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey())
                .compact();
    }

    public String getUserDetails(String token){
        Claims claims =  Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String email = claims.getSubject();
        Long id = claims.get("id",Long.class);

        return email;
    }
}
