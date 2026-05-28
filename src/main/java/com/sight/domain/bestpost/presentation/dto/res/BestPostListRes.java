package com.sight.domain.bestpost.presentation.dto.res;

import com.sight.domain.bestpost.domain.BestPost;

import java.time.LocalDateTime;

public record BestPostListRes(
        Long id,
        Long postId,
        LocalDateTime created_at
) {
    public static BestPostListRes from(BestPost bestPost) {
        return new BestPostListRes(
                bestPost.getId(),
                bestPost.getPostId(),
                bestPost.getCreated_at()
        );
    }
}
