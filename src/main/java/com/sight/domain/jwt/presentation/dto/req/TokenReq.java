package com.sight.domain.jwt.presentation.dto.req;

public record TokenReq(
        String accessToken,
        String refreshToken
) {
}