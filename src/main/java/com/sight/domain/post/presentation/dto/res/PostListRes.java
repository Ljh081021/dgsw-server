package com.sight.domain.post.presentation.dto.res;

import com.sight.domain.post.domain.Post;
import com.sight.domain.user.domain.User;

import java.time.LocalDateTime;

public record PostListRes(
        Long id,
        String title,
        double latitude,
        double longitude,
        Long writer,
        int likeNum,
        LocalDateTime created_at
) {
    public static PostListRes from(Post post) {
        return new PostListRes(
                post.getId(),
                post.getTitle(),
                post.getLatitude(),
                post.getLongitude(),
                post.getWriterId(),
                post.getLikeNum(),
                post.getCreated_at()
        );
    }
}
