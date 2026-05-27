package com.sight.domain.user.presentation;

import com.sight.domain.user.presentation.dto.req.UserCreateReq;
import com.sight.domain.user.presentation.dto.req.UserUpdateReq;
import com.sight.domain.user.presentation.dto.res.UserRes;
import com.sight.domain.user.service.UserService;
import com.sight.global.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Response signup(@RequestBody UserCreateReq req) {
        return userService.signup(req);
    }

    @GetMapping("/one")
    public UserRes getMember() {
        return userService.findOneUser();
    }

    @PutMapping("/update")
    public Response updateMember(@RequestBody UserUpdateReq req) {
        return userService.updateUser(req);
    }

    @DeleteMapping("/delete")
    public Response deleteMember() {
        return userService.deleteUser();
    }
}
