package com.sight.domain.jwt.presentation.dto.res;

public record TokenRes(
        String grantType,
        String accessToken
) {}
