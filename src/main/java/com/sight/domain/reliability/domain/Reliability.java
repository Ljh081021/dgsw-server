package com.sight.domain.reliability.domain;

import com.sight.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reliability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double reliability;

    @Column(nullable = false)
    private Long rater;

    @Column(nullable = false)
    private Long target;

    @Column(nullable = false)
    private LocalDateTime created_at;
}
