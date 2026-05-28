package com.sight.domain.post.service;

import com.sight.domain.post.domain.Post;
import com.sight.domain.post.domain.enums.Category;
import com.sight.domain.post.domain.enums.Region;
import com.sight.domain.post.domain.repo.PostRepo;
import com.sight.domain.post.error.PostErrorCode;
import com.sight.domain.post.presentation.dto.req.*;
import com.sight.domain.post.presentation.dto.res.PostListRes;
import com.sight.domain.post.presentation.dto.res.PostRes;
import com.sight.global.exception.CustomException;
import com.sight.global.response.Response;
import com.sight.global.security.usecase.UserSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserSessionHolder userSessionHolder;

    @Transactional
    public Response save(PostCreateReq req) {
        if (req.title() == null) {
            throw new CustomException(PostErrorCode.EMPTY_TITLE);
        }

        Post post = req.to(userSessionHolder.getUser().getId());
        postRepo.save(post);
        return Response.created("게시물이 정상적으로 작성 되었습니다.");
    }

    public PostRes findById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new CustomException(PostErrorCode.POST_NOT_FOUND)
        );
        return PostRes.from(post);
    }

    public List<PostListRes> findByScreenAndUserPosition(
            String latitude,
            String longitude,
            int screenWidth,
            int screenHeight,
            Double zoomLevel
    ) {
        double userLat = Double.parseDouble(latitude);
        double userLon = Double.parseDouble(longitude);

        double[] boundingBox = calculateBoundingBox(userLat, userLon, screenWidth, screenHeight, zoomLevel);
        double minLat = boundingBox[0];
        double maxLat = boundingBox[1];
        double minLon = boundingBox[2];
        double maxLon = boundingBox[3];

        return postRepo.findAll().stream()
                .filter(post -> post.getLatitude() >= minLat && post.getLatitude() <= maxLat
                        && post.getLongitude() >= minLon && post.getLongitude() <= maxLon)
                .map(PostListRes::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Response update(PostUpdateReq req, Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new CustomException(PostErrorCode.POST_NOT_FOUND)
        );
        post.update(req);
        postRepo.save(post);
        return Response.ok("게시물이 정상적으로 업데이트 되었습니다.");
    }

    @Transactional
    public Response delete(Long postId) {
        postRepo.deleteById(postId);
        return Response.noContent("게시물이 정상적으로 삭제 되었습니다.");
    }

    public List<PostListRes> search(Region region, Category category, String tag, Double congestion) {
        return postRepo.findAll().stream()
                .filter(post -> region == null || post.getRegion() == region)
                .filter(post -> category == null || post.getCategory() == category)
                .filter(post -> tag == null || post.getTags().contains(tag))
                .filter(post -> congestion == null || post.getCongestion() <= congestion)
                .map(PostListRes::from)
                .collect(Collectors.toList());
    }

    public List<PostListRes> recommend(Region region) {
        return postRepo.findAll().stream()
                .filter(post -> region == null || post.getRegion() == region)
                .sorted(Comparator.comparingInt(Post::getLikeNum).reversed())
                .map(PostListRes::from)
                .collect(Collectors.toList());
    }

    public void updateLikeNum(Long postId, int num) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new CustomException(PostErrorCode.POST_NOT_FOUND)
        );
        post.updateLike(num);
    }

    private double[] calculateBoundingBox(double lat, double lon,
                                          int screenWidth, int screenHeight,
                                          double zoomLevel) {

        double metersPerPixel = 156543.03392 * Math.cos(Math.toRadians(lat)) / Math.pow(2, zoomLevel);
        double kmPerPixel = metersPerPixel / 1000;

        double halfWidthKm  = (screenWidth / 2.0) * kmPerPixel;
        double halfHeightKm = (screenHeight / 2.0) * kmPerPixel;

        double latDelta = halfHeightKm / 111.0;
        double lonDelta = halfWidthKm  / (111.0 * Math.cos(Math.toRadians(lat)));

        return new double[]{
                lat - latDelta,
                lat + latDelta,
                lon - lonDelta,
                lon + lonDelta
        };
    }

    //test
    //{
    //  "latitude": "37.5665",
    //  "longitude": "126.9780",
    //  "northEastLat": "37.5800",
    //  "northEastLon": "126.9900",
    //  "southWestLat": "37.5500",
    //  "southWestLon": "126.9600"
    //}
}
