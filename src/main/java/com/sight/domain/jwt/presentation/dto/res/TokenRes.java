package com.sight.domain.jwt.presentation.dto.res;

public record TokenRes(
        String accessToken,
        String refreshToken
) {}
