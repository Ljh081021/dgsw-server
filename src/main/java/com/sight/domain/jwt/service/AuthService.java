package com.sight.domain.jwt.service;

import com.sight.domain.jwt.domain.RefreshToken;
import com.sight.domain.jwt.domain.repo.RefreshTokenRepo;
import com.sight.domain.jwt.error.AuthErrorCode;
import com.sight.domain.jwt.presentation.dto.req.LoginReq;
import com.sight.domain.jwt.presentation.dto.req.TokenReq;
import com.sight.domain.jwt.presentation.dto.res.LoginRes;
import com.sight.domain.jwt.presentation.dto.res.TokenRes;
import com.sight.domain.user.domain.User;
import com.sight.domain.user.domain.repo.UserRepo;
import com.sight.domain.user.error.UserErrorCode;
import com.sight.domain.user.presentation.dto.req.UserCreateReq;
import com.sight.global.exception.CustomException;
import com.sight.global.response.Response;
import com.sight.global.security.auth.AuthDetails;
import com.sight.global.security.jwt.JwtProperties;
import com.sight.global.security.jwt.util.TokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public LoginRes login(LoginReq req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.name(), req.password()));
        User user = ((AuthDetails) authentication.getPrincipal()).getUser();
        Duration accessExpiration = Duration.ofMinutes(jwtProperties.getAccessExpirationMinutes());
        Duration refreshExpiration = Duration.ofDays(jwtProperties.getRefreshExpirationDays());
        String accessToken = tokenProvider.generateToken(user, accessExpiration);
        String refreshToken = tokenProvider.generateToken(user, refreshExpiration);
        refreshTokenService.saveOrUpdate(user.getId(), refreshToken);
        return new LoginRes(accessToken, refreshToken);
    }

    // 토큰 재발급
    @Transactional
    public TokenRes reissue(TokenReq req) {

        if (!tokenProvider.validToken(req.refreshToken())) {
            throw new CustomException(AuthErrorCode.INVALID_REFRESH_TOKEN);
        }

        Authentication authentication =
                tokenProvider.getAuthentication(req.accessToken());

        User user =
                ((AuthDetails) authentication.getPrincipal()).getUser();

        RefreshToken refreshToken =
                refreshTokenService.findByRefreshToken(req.refreshToken());

        if (!refreshToken.getRefreshToken().equals(req.refreshToken())) {
            throw new CustomException(AuthErrorCode.TOKEN_MISMATCH);
        }

        Duration accessExpiration =
                Duration.ofMinutes(jwtProperties.getAccessExpirationMinutes());

        Duration refreshExpiration =
                Duration.ofDays(jwtProperties.getRefreshExpirationDays());

        String accessToken =
                tokenProvider.generateToken(user, accessExpiration);

        String newRefreshToken =
                tokenProvider.generateToken(user, refreshExpiration);

        refreshTokenService.saveOrUpdate(user.getId(), newRefreshToken);

        return new TokenRes(accessToken, newRefreshToken);
    }
}
