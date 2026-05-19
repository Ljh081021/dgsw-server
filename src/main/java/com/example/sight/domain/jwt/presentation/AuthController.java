package com.example.sight.domain.jwt.presentation;

import com.example.sight.domain.jwt.presentation.dto.req.LoginReq;
import com.example.sight.domain.jwt.presentation.dto.req.TokenReq;
import com.example.sight.domain.jwt.presentation.dto.res.TokenRes;
import com.example.sight.domain.jwt.service.AuthService;
import com.example.sight.domain.user.presentation.dto.req.UserCreateReq;
import com.example.sight.global.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public Response signup(@RequestBody UserCreateReq req) {
        return authService.signup(req);
    }

    @PostMapping("/login")
    public TokenRes login(@RequestBody LoginReq req) {
        return authService.login(req);
    }

    @PostMapping("/reissue")
    public TokenRes reissue(@RequestBody TokenReq req) {
        return authService.reissue(req);
    }
}