package com.sight.domain.jwt.presentation.dto.res;

public record LoginRes(
        String accessToken,
        String refreshToken
) {}
