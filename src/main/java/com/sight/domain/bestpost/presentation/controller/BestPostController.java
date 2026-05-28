package com.sight.domain.bestpost.presentation.controller;

import com.sight.domain.bestpost.presentation.dto.res.BestPostListRes;
import com.sight.domain.bestpost.service.BestPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BestPostController {

    private final BestPostService bestPostService;

    @GetMapping("/all")
    public List<BestPostListRes> getAll() {
        return bestPostService.findAll();
    }
}
