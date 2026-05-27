package com.sight.domain.image.error;

import com.sight.global.exception.error.CustomErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ImageErrorCode implements CustomErrorCode {

    UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.");

    private final HttpStatus status;
    private final String message;
}