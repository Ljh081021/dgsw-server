package com.sight.domain.user.domain;

import com.sight.domain.user.presentation.dto.req.UserUpdateReq;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

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
    private LocalDateTime created_at;

    public void update(UserUpdateReq req) {
        this.name = req.name();
    }

    @Builder
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.created_at = LocalDateTime.now();
    }
}