package com.example.sight.domain.user.presentation;

import com.example.sight.domain.user.presentation.dto.req.UserUpdateReq;
import com.example.sight.domain.user.presentation.dto.res.UserRes;
import com.example.sight.domain.user.service.UserService;
import com.example.sight.global.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserRes getMember(@PathVariable("id") Long id) {
        return userService.findOneMember(id);
    }

    @PutMapping("/{id}")
    public Response updateMember(@PathVariable("id") Long id, @RequestBody UserUpdateReq req) {
        return userService.updateMember(id, req);
    }

    @DeleteMapping("/{id}")
    public Response deleteMember(@PathVariable("id") Long id) {
        return userService.deleteMember(id);
    }
}
