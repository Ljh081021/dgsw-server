package com.example.sight.domain.jwt.service;

import com.example.sight.domain.jwt.domain.RefreshToken;
import com.example.sight.domain.jwt.domain.repo.RefreshTokenRepo;
import com.example.sight.domain.jwt.error.AuthErrorCode;
import com.example.sight.domain.jwt.presentation.dto.req.LoginReq;
import com.example.sight.domain.jwt.presentation.dto.req.TokenReq;
import com.example.sight.domain.jwt.presentation.dto.res.TokenRes;
import com.example.sight.domain.user.domain.User;
import com.example.sight.domain.user.domain.repo.UserRepo;
import com.example.sight.domain.user.error.UserErrorCode;
import com.example.sight.domain.user.presentation.dto.req.UserCreateReq;
import com.example.sight.global.exception.CustomException;
import com.example.sight.global.response.Response;
import com.example.sight.global.security.auth.AuthDetails;
import com.example.sight.global.security.usecase.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepo refreshTokenRepository;

    // 회원가입
    public Response signup(UserCreateReq req) {

        if (userRepo.existsByName(req.name())) {
            throw new CustomException(UserErrorCode.DUPLICATED_NAME);
        }

        User user = req.toUser(passwordEncoder);
        userRepo.save(user);

        return Response.created("회원가입이 왑료되었습니다.");
    }

    // 로그인
    public TokenRes login(LoginReq req) {

        // 1) uid + password → AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                req.toAuthentication();

        // 2) DB 검증 → CustomUserDetailsService.loadUserByUsername 실행됨
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3) Member 조회
        User user = userRepo.findByName(req.name())
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        // 4) AuthDetails 기반 Authentication 재구성
        AuthDetails authDetails = new AuthDetails(user);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                authDetails,
                authentication.getCredentials(),
                authentication.getAuthorities()
        );

        // 5) JWT 생성
        TokenRes tokenRes = tokenProvider.generateTokenRes(newAuth);

        // 6) Refresh Token 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(user.getName())
                .value(tokenRes.refreshToken())   // record 방식
                .build();

        refreshTokenRepository.save(refreshToken);

        // 7) 토큰 반환
        return tokenRes;
    }

    // 토큰 재발급
    public TokenRes reissue(TokenReq req) {

        // 1) Refresh Token 유효성 검사
        if (!tokenProvider.validateToken(req.refreshToken())) {
            throw new CustomException(AuthErrorCode.INVALID_REFRESH_TOKEN);
        }

        // 2) Access Token에서 사용자 정보 추출
        Authentication authentication =
                tokenProvider.getAuthentication(req.accessToken());

        // 3) DB에 저장된 Refresh Token 조회
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new CustomException(AuthErrorCode.LOGGED_OUT_USER));

        // 4) Refresh Token 일치 여부 체크
        if (!refreshToken.getValue().equals(req.refreshToken())) {
            throw new CustomException(AuthErrorCode.TOKEN_MISMATCH);
        }

        // 5) 새로운 JWT 발급
        TokenRes tokenRes = tokenProvider.generateTokenRes(authentication);

        // 6) Refresh Token 갱신
        RefreshToken newRefreshToken =
                refreshToken.updateValue(tokenRes.refreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 7) 최종 반환
        return tokenRes;
    }
}
