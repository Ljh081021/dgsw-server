package com.sight.domain.bestpost.service;

import com.sight.domain.bestpost.domain.BestPost;
import com.sight.domain.bestpost.domain.repo.BestPostRepo;
import com.sight.domain.bestpost.presentation.dto.res.BestPostListRes;
import com.sight.domain.post.domain.Post;
import com.sight.domain.post.domain.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BestPostService {

    private final BestPostRepo bestPostRepo;
    private final PostRepo postRepo;

    @Transactional
    public void save() {
        postRepo.findAll().stream()
                .max(Comparator.comparingInt(Post::getLikeNum))
                .ifPresent(post -> {
                    BestPost bestPost = BestPost.builder()
                            .postId(post.getId())
                            .created_at(LocalDateTime.now())
                            .build();
                    bestPostRepo.save(bestPost);
                });
    }

    public List<BestPostListRes> findAll() {
        return bestPostRepo.findAll().stream()
                .sorted(Comparator.comparing(BestPost::getCreated_at).reversed())
                .map(BestPostListRes::from)
                .collect(Collectors.toList());
    }
}
