package com.sight.domain.post.domain;

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

    @ElementCollection
    private List<String> imageUrl;
}
