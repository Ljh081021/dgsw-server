package com.sight.domain.jwt.service;

import com.sight.domain.jwt.domain.RefreshToken;
import com.sight.domain.jwt.domain.repo.RefreshTokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepo refreshTokenRepo;

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepo
                .findByRefreshToken(refreshToken)
                .orElseThrow(
                        () -> new IllegalArgumentException("Unexpected token")
                );
    }

    @Transactional
    public RefreshToken saveOrUpdate(Long userId, String refreshToken) {
        return refreshTokenRepo.findByUserId(userId)
                .map(entity -> entity.update(refreshToken))
                .map(refreshTokenRepo::save)
                .orElseGet(() -> refreshTokenRepo.save(new RefreshToken(userId, refreshToken)));

    }
}
