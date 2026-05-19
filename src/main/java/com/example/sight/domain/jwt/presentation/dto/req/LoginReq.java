package com.example.sight.domain.jwt.presentation.dto.req;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginReq(
        String name,
        String password
) {
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(name, password);
    }
}
