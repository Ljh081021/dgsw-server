package com.sight.domain.user.presentation.dto.res;

import com.sight.domain.user.domain.User;

import java.time.LocalDateTime;

public record UserRes(
        String name,
        String password,
        String profile,
        LocalDateTime created_at
) {
    public static UserRes from(User user) {
        return new UserRes(
                user.getName(),
                user.getPassword(),
                user.getProfile(),
                user.getCreated_at()
        );
    }
}
