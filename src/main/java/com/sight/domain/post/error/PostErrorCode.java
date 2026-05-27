package com.sight.domain.post.error;

import com.sight.global.exception.error.CustomErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode implements CustomErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물을 찾을 수 없습니다."),
    EMPTY_TITLE(HttpStatus.BAD_REQUEST, "제목은 필수로 입력해야 합니다.");

    private final HttpStatus status;
    private final String message;
}