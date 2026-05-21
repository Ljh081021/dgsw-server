package com.sight.global.security.usecase;

import com.sight.domain.user.domain.User;
import com.sight.domain.user.error.UserErrorCode;
import com.sight.global.exception.CustomException;
import com.sight.global.security.auth.AuthDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSessionHolder {

    public User getUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                authentication instanceof AnonymousAuthenticationToken) {
            throw new CustomException(UserErrorCode.USER_NOT_FOUND);
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof AuthDetails authDetails) {
            return authDetails.getUser();
        }

        throw new CustomException(UserErrorCode.USER_NOT_FOUND);
    }
}