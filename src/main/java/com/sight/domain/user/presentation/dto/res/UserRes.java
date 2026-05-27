package com.sight.domain.user.presentation.dto.res;

import com.sight.domain.user.domain.User;
import jakarta.persistence.ElementCollection;

import java.time.LocalDateTime;
import java.util.List;

public record UserRes(
        Long id,
        String name,
        String profile,
        LocalDateTime created_at,
        List<Long> liked,
        List<Long> disliked,
        List<Long> bookmarked
) {
    public static UserRes from(User user) {
        return new UserRes(
                user.getId(),
                user.getName(),
                user.getProfile(),
                user.getCreated_at(),
                user.getLiked(),
                user.getDisliked(),
                user.getBookmarked()
        );
    }
}
