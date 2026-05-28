package com.sight.domain.user.presentation.dto.req;

import com.sight.domain.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record UserCreateReq(
        String name,
        String profile,
        String password
) {
    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .profile(profile != null ? profile : "https://sight.s3.ap-northeast-2.amazonaws.com/default_profile.png")
                .password(passwordEncoder.encode(password))
                .liked(new ArrayList<>())
                .created_at(LocalDateTime.now())
                .bookmarked(new ArrayList<>())
                .build();
    }
}
