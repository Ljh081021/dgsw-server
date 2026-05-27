package com.sight.domain.post.presentation.dto.res;

import com.sight.domain.post.domain.Post;
import com.sight.domain.user.domain.User;

import java.time.LocalDateTime;

public record PostListRes(
        Long id,
        String title,
        double latitude,
        double longitude,
        LocalDateTime created_at,
        User writer
) {
    public static PostListRes from(Post post) {
        return new PostListRes(
                post.getId(),
                post.getTitle(),
                post.getLatitude(),
                post.getLongitude(),
                post.getCreated_at(),
                post.getWriter()
        );
    }
}
