package com.example.sight.domain.jwt.error;

import com.example.sight.global.exception.error.CustomErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements CustomErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Refresh Token이 유효하지 않습니다."),
    LOGGED_OUT_USER(HttpStatus.UNAUTHORIZED, "로그아웃된 사용자입니다."),
    TOKEN_MISMATCH(HttpStatus.UNAUTHORIZED, "토큰의 유저 정보가 일치하지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");

    private final HttpStatus status;
    private final String message;
}