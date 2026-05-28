package com.sight.domain.post.presentation.dto.req;

public record PostGetReq(
        String latitude,
        String longitude,
        int screenWidth,
        int screenHeight,
        Double zoomLevel
) {
}
