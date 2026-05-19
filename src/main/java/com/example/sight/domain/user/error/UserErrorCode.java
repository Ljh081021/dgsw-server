package com.example.sight.domain.user.error;

import com.example.sight.global.exception.error.CustomErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements CustomErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾지 못하였습니다."),
    DUPLICATED_NAME(HttpStatus.BAD_REQUEST, "이미 존재하는 이름입니다."),
    ALREADY_EXISTS_USER(HttpStatus.CONFLICT, "이미 가입되어 있는 유저입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근이 거부되었습니다.");

    private final HttpStatus status;
    private final String message;
}