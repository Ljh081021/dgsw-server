package com.sight.domain.jwt.service;

import com.sight.domain.jwt.domain.RefreshToken;
import com.sight.domain.jwt.error.AuthErrorCode;
import com.sight.domain.jwt.presentation.dto.req.LoginReq;
import com.sight.domain.jwt.presentation.dto.req.TokenReq;
import com.sight.domain.jwt.presentation.dto.res.TokenRes;
import com.sight.domain.user.domain.User;
import com.sight.global.exception.CustomException;
import com.sight.global.security.auth.AuthDetails;
import com.sight.global.security.jwt.JwtProperties;
import com.sight.global.security.jwt.util.TokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final JwtProperties jwtProperties;

    // 로그인
    @Transactional
    public TokenRes login(LoginReq req) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.name(), req.password())
        );

        User user = ((AuthDetails) authentication.getPrincipal()).getUser();

        return issueTokens(user);
    }

    // 토큰 재발급
    @Transactional
    public TokenRes reissue(TokenReq req) {

        validateRefreshToken(req.refreshToken());

        Authentication authentication =
                tokenProvider.getAuthentication(req.accessToken());

        User user = ((AuthDetails) authentication.getPrincipal()).getUser();

        return issueTokens(user);
    }

    private TokenRes issueTokens(User user) {

        Duration accessExpiration =
                Duration.ofMinutes(jwtProperties.getAccessExpirationMinutes());

        Duration refreshExpiration =
                Duration.ofDays(jwtProperties.getRefreshExpirationDays());

        String accessToken = tokenProvider.generateToken(user, accessExpiration);
        String refreshToken = tokenProvider.generateToken(user, refreshExpiration);

        refreshTokenService.saveOrUpdate(user.getId(), refreshToken);

        return new TokenRes(accessToken, refreshToken);
    }

    private void validateRefreshToken(String refreshToken) {

        if (!tokenProvider.validToken(refreshToken)) {
            throw new CustomException(AuthErrorCode.INVALID_REFRESH_TOKEN);
        }

        RefreshToken stored =
                refreshTokenService.findByRefreshToken(refreshToken);

        if (!stored.getRefreshToken().equals(refreshToken)) {
            throw new CustomException(AuthErrorCode.TOKEN_MISMATCH);
        }
    }
}
