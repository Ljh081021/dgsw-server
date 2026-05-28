package com.sight.global.scheduler;

import com.sight.domain.bestpost.service.BestPostService;
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
    private final BestPostService bestPostService;

    @Scheduled(cron = "0 0 0 1 * *")
    public void resetLikeCount() {
        bestPostService.save();

        postRepo.findAll().forEach(post -> {
            post.resetLike();
            postRepo.save(post);
        });
    }
}