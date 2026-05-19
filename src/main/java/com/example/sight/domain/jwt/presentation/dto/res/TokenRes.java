package com.example.sight.domain.jwt.presentation.dto.res;

public record TokenRes(
        String grantType,
        String accessToken,
        Long accessTokenExpiresIn,
        String refreshToken
) {
    public static TokenRes from(
            String grantType,
            String accessToken,
            Long expiresIn,
            String refreshToken
    ) {
        return new TokenRes(
                grantType,
                accessToken,
                expiresIn,
                refreshToken
        );
    }
}
