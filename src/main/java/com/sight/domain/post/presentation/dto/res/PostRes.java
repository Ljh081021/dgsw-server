package com.sight.domain.post.presentation.dto.res;

import com.sight.domain.post.domain.Post;
import com.sight.domain.user.domain.User;

import java.util.List;

public record PostRes(
        Long id,
        String title,
        double latitude,
        double longitude,
        String content,
        User writer,
        List<String> imageUrl
) {
    public static PostRes from(Post post) {
        return new PostRes(
                post.getId(),
                post.getTitle(),
                post.getLatitude(),
                post.getLongitude(),
                post.getContent(),
                post.getWriter(),
                post.getImageUrls()
        );
    }
}
