package com.sight.domain.jwt.presentation.dto.req;

public record LoginReq(
        String name,
        String password
) {}
