package com.sight.domain.post.domain;

import com.sight.domain.post.domain.enums.Category;
import com.sight.domain.post.domain.enums.Region;
import com.sight.domain.post.presentation.dto.req.PostUpdateReq;
import com.sight.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column
    private String content;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Region region;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Double congestion;

    @Column(nullable = false)
    private int likeNum;

    @Column
    private LocalDateTime updated_at;

    @Column(nullable = false)
    private Long writerId;

    @ElementCollection
    private List<String> tags;

    @ElementCollection
    private List<String> imageUrls;

    public void update(PostUpdateReq req) {
        this.title = req.title();
        this.content = req.content();
        this.imageUrls = req.imageUrls();
        this.updated_at = LocalDateTime.now();
    }

    public void updateLike(int num) {
        this.likeNum += num;
    }

    public void resetLike() {
        this.likeNum = 0;
    }
}
