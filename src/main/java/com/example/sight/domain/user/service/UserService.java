package com.example.sight.domain.user.service;

import com.example.sight.domain.user.domain.User;
import com.example.sight.domain.user.domain.repo.UserRepo;
import com.example.sight.domain.user.presentation.dto.req.UserUpdateReq;
import com.example.sight.domain.user.presentation.dto.res.UserRes;
import com.example.sight.global.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserRes findOneMember(Long id) {
        User user = userRepo.findById(id).orElseThrow();

        return UserRes.from(user);
    }

    public Response updateMember(Long id, UserUpdateReq req) {
        User user = userRepo.findById(id).orElseThrow();

        user.update(req);

        return Response.ok("정상적으로 수정되었습니다.");
    }

    public Response deleteMember(Long id) {
        userRepo.deleteById(id);

        return Response.ok("정상적으로 삭제되었습니다.");
    }
}
