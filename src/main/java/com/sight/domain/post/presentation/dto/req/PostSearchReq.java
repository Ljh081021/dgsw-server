package com.sight.domain.post.presentation.dto.req;

import com.sight.domain.post.domain.enums.Category;
import com.sight.domain.post.domain.enums.Region;

public record PostSearchReq(
        Region region,
        Category category,
        Double congestion,
        String tag
) {
}
