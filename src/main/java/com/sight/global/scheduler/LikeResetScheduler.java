package com.sight.global.scheduler;

import com.sight.domain.post.domain.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@RequiredArgsConstructor
public class LikeResetScheduler {

    private final PostRepo postRepo;

    @Scheduled(cron = "0 0 0 1 * *") // 매월 1일 자정
    public void resetLikeCount() {
        postRepo.findAll().forEach(post -> {
            post.resetLike();
            postRepo.save(post);
        });
    }
}