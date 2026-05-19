package com.example.sight.domain.user.domain;

import com.example.sight.domain.user.domain.enums.Authority;
import com.example.sight.domain.user.presentation.dto.req.UserUpdateReq;
import jakarta.persistence.*;
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

    @Column(unique = true)
    private String name;

    @Column
    private String password;

    @Column
    private Authority authority;

    @Column
    private LocalDateTime created_at;

    public void update(UserUpdateReq req) {
        this.name = req.name();
    }
}