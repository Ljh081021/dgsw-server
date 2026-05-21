package com.sight.domain.user.presentation.dto.req;

import com.sight.domain.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public record UserCreateReq(
        String name,
        String password
) {
    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
