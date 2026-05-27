package com.sight.domain.post.domain;

import com.sight.domain.post.presentation.dto.req.PostUpdateReq;
import com.sight.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private String content;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

    @ManyToOne
    private User writer;

    @ElementCollection
    private List<String> imageUrls;

    public void update(PostUpdateReq req) {
        this.title = req.title();
        this.content = req.content();
        this.imageUrls = req.imageUrls();
        this.updated_at = LocalDateTime.now();
    }
}
