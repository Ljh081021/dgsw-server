package com.sight.domain.post.presentation.dto.req;

import java.util.List;

public record PostUpdateReq(
        String title,
        String content,
        List<String> imageUrls
) {
}
