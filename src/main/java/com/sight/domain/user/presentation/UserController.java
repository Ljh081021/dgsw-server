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

    @GetMapping("/{id}")
    public UserRes getMember(@PathVariable("id") Long id) {
        return userService.findOneUser(id);
    }

    @PutMapping("/{id}")
    public Response updateMember(@PathVariable("id") Long id, @RequestBody UserUpdateReq req) {
        return userService.updateUser(id, req);
    }

    @DeleteMapping("/{id}")
    public Response deleteMember(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }
}
