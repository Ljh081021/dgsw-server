package com.sight.domain.user.service;

import com.sight.domain.post.domain.Post;
import com.sight.domain.post.domain.repo.PostRepo;
import com.sight.domain.post.error.PostErrorCode;
import com.sight.domain.post.service.PostService;
import com.sight.domain.reliability.service.ReliabilityService;
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
    private final ReliabilityService reliabilityService;
    private final PostRepo postRepo;
    private final PostService postService;

    @Transactional
    public Response signup(UserCreateReq req) {
        if (req.name() == null) throw new CustomException(UserErrorCode.EMPTY_NAME);
        if (req.password() == null) throw new CustomException(UserErrorCode.EMPTY_PASSWORD);
        if (userRepo.existsByName(req.name())) throw new CustomException(UserErrorCode.DUPLICATED_NAME);

        User user = req.toUser(passwordEncoder);
        userRepo.save(user);

        return Response.created("회원가입이 왑료되었습니다.");
    }

    public UserRes findOneUser() {
        User user = userSessionHolder.getUser();
        double reliability = reliabilityService.averageReliability(user.getId());
        return UserRes.from(user, reliability);
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

    @Transactional
    public Response likePost(Long postId) {
        userSessionHolder.getUser().like(postId);
        postService.updateLikeNum(postId, 1);
        return Response.ok("게시물에 좋아요를 누르셨습니다.");
    }

    @Transactional
    public Response dislikePost(Long postId) {
        userSessionHolder.getUser().dislike(postId);
        postService.updateLikeNum(postId, -1);
        return Response.ok("게시물에 좋아요를 취소 하셨습니다.");
    }

    @Transactional
    public Response bookmarkPost(Long postId) {
        userSessionHolder.getUser().bookmark(postId);
        return Response.ok("게시물을 북마크에 추가하셨습니다.");
    }
}
