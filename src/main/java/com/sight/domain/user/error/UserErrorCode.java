package com.sight.domain.user.error;

import com.sight.global.exception.error.CustomErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements CustomErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾지 못하였습니다."),
    DUPLICATED_NAME(HttpStatus.BAD_REQUEST, "이미 존재하는 이름입니다."),
    EMPTY_NAME(HttpStatus.BAD_REQUEST, "이름은 필수로 입력해야 합니다."),
    EMPTY_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 필수로 입력해야 합니다.");

    private final HttpStatus status;
    private final String message;
}