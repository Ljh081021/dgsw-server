package com.example.sight.domain.user.presentation.dto.res;

import com.example.sight.domain.user.domain.User;

import java.time.LocalDateTime;

public record UserRes(
        String name,
        String password,
        LocalDateTime created_at
) {
    public static UserRes from(User user) {
        return new UserRes(
                user.getName(),
                user.getPassword(),
                user.getCreated_at()
        );
    }
}
