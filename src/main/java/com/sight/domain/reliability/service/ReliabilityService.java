package com.sight.domain.reliability.service;

import com.sight.domain.reliability.domain.Reliability;
import com.sight.domain.reliability.domain.repo.ReliabilityRepo;
import com.sight.domain.reliability.presentation.dto.req.ReliabilityCreateReq;
import com.sight.domain.user.domain.User;
import com.sight.global.response.Response;
import com.sight.global.security.usecase.UserSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReliabilityService {

    private final ReliabilityRepo reliabilityRepo;
    private final UserSessionHolder userSessionHolder;

    @Transactional
    public Response save(ReliabilityCreateReq req, boolean first) {
        Long raterId;
        if (first) {
            raterId = 9223372036854775807L;
        } else {
            raterId = userSessionHolder.getUser().getId();
        }
        Reliability reliability = req.to(raterId);
        reliabilityRepo.save(reliability);
        return Response.created("유저의 신뢰도가 등록되었습니다.");
    }

    public double averageReliability(Long userId) {
        List<Reliability> reliabilityList = reliabilityRepo.findByTarget(userId);
        return reliabilityList.stream()
                .mapToDouble(Reliability::getReliability)
                .average()
                .orElse(0.0);
    }
}
