package com.sight.domain.reliability.presentation.dto.req;

import com.sight.domain.reliability.domain.Reliability;

import java.time.LocalDateTime;

public record ReliabilityCreateReq(
        double reliability,
        Long target
) {
    public Reliability to(Long rater) {
        return Reliability.builder()
                .reliability(reliability)
                .rater(rater)
                .target(target)
                .created_at(LocalDateTime.now())
                .build();
    }
}
