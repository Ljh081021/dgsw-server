package com.sight.domain.post.presentation.dto.req;

import com.sight.domain.post.domain.Post;
import com.sight.domain.post.domain.enums.Category;
import com.sight.domain.post.domain.enums.Region;
import com.sight.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public record PostCreateReq(
        String title,
        Double latitude,
        Double longitude,
        String content,
        Region region,
        Category category,
        Double congestion,
        List<String> tags,
        List<String> imageUrls
) {
    public Post to(Long writer) {
        return Post.builder()
                .title(title)
                .latitude(latitude)
                .longitude(longitude)
                .content(content)
                .region(region)
                .category(category)
                .congestion(congestion)
                .tags(tags)
                .created_at(LocalDateTime.now())
                .writerId(writer)
                .imageUrls(imageUrls)
                .build();
    }
}
