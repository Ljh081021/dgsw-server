package com.sight.domain.user.domain;

import com.sight.domain.user.presentation.dto.req.UserUpdateReq;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column
    private String profile;

    @Column
    private LocalDateTime created_at;

    @ElementCollection
    private List<Long> liked;

    @ElementCollection
    private List<Long> disliked;

    @ElementCollection
    private List<Long> bookmarked;

    public void update(UserUpdateReq req) {
        this.name = req.name();
        this.profile = req.profile();
    }

    public void like(Long postId) {
        this.liked.add(postId);
    }

    public void dislike(Long postId) {
        this.disliked.add(postId);
    }

    public void bookmark(Long postId) {
        this.bookmarked.add(postId);
    }
}