package com.sight.domain.post.presentation.controller;

import com.sight.domain.post.presentation.dto.req.PostCreateReq;
import com.sight.domain.post.presentation.dto.req.PostGetReq;
import com.sight.domain.post.presentation.dto.req.PostSearchReq;
import com.sight.domain.post.presentation.dto.req.PostUpdateReq;
import com.sight.domain.post.presentation.dto.res.PostListRes;
import com.sight.domain.post.presentation.dto.res.PostRes;
import com.sight.domain.post.service.PostService;
import com.sight.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/save")
    public Response save(@RequestBody PostCreateReq req) {
        return postService.save(req);
    }

    @GetMapping("/one/{id}")
    public PostRes one(@PathVariable Long id) {
        return postService.findById(id);
    }

    @GetMapping("/all")
    public List<PostListRes> findByScreenAndUserPosition(@RequestBody PostGetReq req) {
        return postService.findByScreenAndUserPosition(req);
    }

    @PutMapping("/update/{id}")
    public Response update(@PathVariable Long id, @RequestBody PostUpdateReq req) {
        return postService.update(req, id);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return postService.delete(id);
    }

    @GetMapping("/search")
    public List<PostListRes> search(@RequestBody PostSearchReq req) {
        return postService.search(req);
    }
}
