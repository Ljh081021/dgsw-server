package com.example.sight.global.exception;

import com.example.sight.global.exception.error.CustomErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final CustomErrorCode error;
}
