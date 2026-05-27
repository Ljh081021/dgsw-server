package com.sight.domain.post.presentation.dto.req;

import com.sight.domain.post.domain.Post;
import com.sight.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public record PostCreateReq(
        String title,
        double latitude,
        double longitude,
        String content,
        List<String> imageUrls
) {
    public Post to(User writer) {
        return Post.builder()
                .title(title)
                .latitude(latitude)
                .longitude(longitude)
                .content(content)
                .created_at(LocalDateTime.now())
                .writer(writer)
                .imageUrls(imageUrls)
                .build();
    }
}
