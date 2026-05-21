package com.sight.domain.user.service;

import com.sight.domain.user.domain.User;
import com.sight.domain.user.domain.repo.UserRepo;
import com.sight.domain.user.error.UserErrorCode;
import com.sight.domain.user.presentation.dto.req.UserCreateReq;
import com.sight.domain.user.presentation.dto.req.UserUpdateReq;
import com.sight.domain.user.presentation.dto.res.UserRes;
import com.sight.global.exception.CustomException;
import com.sight.global.response.Response;
import com.sight.global.security.usecase.UserSessionHolder;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserSessionHolder userSessionHolder;

    @Transactional
    public Response signup(UserCreateReq req) {

        if (userRepo.existsByName(req.name())) {
            throw new CustomException(UserErrorCode.DUPLICATED_NAME);
        }

        User user = req.toUser(passwordEncoder);
        userRepo.save(user);

        return Response.created("회원가입이 왑료되었습니다.");
    }

    public UserRes findOneUser() {
        User user = userSessionHolder.getUser();
        return UserRes.from(user);
    }

    @Transactional
    public Response updateUser(UserUpdateReq req) {
        User user = userSessionHolder.getUser();
        user.update(req);
        return Response.ok("정상적으로 수정되었습니다.");
    }

    @Transactional
    public Response deleteUser() {
        User user = userSessionHolder.getUser();
        userRepo.delete(user);
        return Response.ok("정상적으로 삭제되었습니다.");
    }
}
