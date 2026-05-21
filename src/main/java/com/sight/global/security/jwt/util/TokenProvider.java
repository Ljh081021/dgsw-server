package com.sight.global.security.jwt.util;

import com.sight.domain.user.domain.User;
import com.sight.domain.user.domain.repo.UserRepo;
import com.sight.global.security.auth.AuthDetails;
import com.sight.global.security.jwt.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private final UserRepo userRepo;
    private SecretKey secretKey;

    @PostConstruct
    void init() {
        byte[] bytes = Decoders.BASE64.decode(
                jwtProperties.getSecretKey()
        );
        this.secretKey = Keys.hmacShaKeyFor(bytes);
    }

    public Authentication getAuthentication(String token){
        String name = getSubject(token);
        User user = userRepo.findByName(name).orElseThrow(
                () -> new UsernameNotFoundException("user not found with name : " + name)
        );
        AuthDetails userDetails = new AuthDetails(user);
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }


    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(
                new Date(now.getTime() + expiredAt.toMillis()),
                user
        );
    }

    private String makeToken(Date expiry, User user) {
        Date now = new Date();
        return Jwts.builder()
                .header().type("JWT").and()
                .issuer(jwtProperties.getIssuer()) // 토큰 발급자
                .issuedAt(now) // 토큰 발급 시간
                .expiration(expiry) // 토큰 만료 시간
                .subject(user.getName()) // 토큰 주체
                .claim("id", user.getId()) //클레임 추가
                .signWith(this.secretKey)
                .compact();
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(this.secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token){
        return getClaims(token).getSubject();
    }

    public Long getUserId(String token){
        return getClaims(token).get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
