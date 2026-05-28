package com.sight.domain.reliability.presentation.controller;

import com.sight.domain.reliability.presentation.dto.req.ReliabilityCreateReq;
import com.sight.domain.reliability.service.ReliabilityService;
import com.sight.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reliability")
public class ReliabilityController {

    private final ReliabilityService reliabilityService;

    @PostMapping("/save")
    public Response save(@RequestBody ReliabilityCreateReq req) {
        return reliabilityService.save(req, false);
    }
}
