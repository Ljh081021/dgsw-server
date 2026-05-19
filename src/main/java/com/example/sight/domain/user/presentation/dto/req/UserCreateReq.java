package com.example.sight.domain.user.presentation.dto.req;

import com.example.sight.domain.user.domain.User;
import com.example.sight.domain.user.domain.enums.Authority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public record UserCreateReq(
        String name,
        String password
) {
    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .authority(Authority.MEMBER)
                .created_at(LocalDateTime.now())
                .build();
    }
}
