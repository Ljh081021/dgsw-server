-- users (password = password123)
INSERT INTO users (name, password, profile, created_at) VALUES ('김민준', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'https://example.com/profile1.png', '2026-05-01 00:00:00');
INSERT INTO users (name, password, profile, created_at) VALUES ('이서연', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'https://example.com/profile2.png', '2026-05-02 00:00:00');

-- post
INSERT INTO post (title, latitude, longitude, content, created_at, region, category, congestion, like_num, writer_id) VALUES ('조용한 북카페', 37.5665, 126.9780, '혼자 있기 좋은 곳', '2026-05-01 00:00:00', 'SEOUL', 'CAFE', 0.2, 10, 1);
INSERT INTO post (title, latitude, longitude, content, created_at, region, category, congestion, like_num, writer_id) VALUES ('한강 산책로', 37.5283, 126.9341, '새벽에 걷기 좋아요', '2026-05-02 00:00:00', 'SEOUL', 'WALK', 0.3, 25, 1);
INSERT INTO post (title, latitude, longitude, content, created_at, region, category, congestion, like_num, writer_id) VALUES ('숨겨진 전망대', 35.1796, 129.0756, '부산 야경 최고', '2026-05-03 00:00:00', 'BUSAN', 'NIGHT_VIEW', 0.1, 18, 2);
INSERT INTO post (title, latitude, longitude, content, created_at, region, category, congestion, like_num, writer_id) VALUES ('제주 숲길', 33.4996, 126.5312, '피톤치드 가득', '2026-05-04 00:00:00', 'JEJU', 'HEALING', 0.4, 30, 2);
INSERT INTO post (title, latitude, longitude, content, created_at, region, category, congestion, like_num, writer_id) VALUES ('대구 근대골목', 35.8714, 128.6014, '혼자 둘러보기 좋음', '2026-05-05 00:00:00', 'DAEGU', 'HIDDEN_SPOT', 0.2, 5, 1);
INSERT INTO post (title, latitude, longitude, content, created_at, region, category, congestion, like_num, writer_id) VALUES ('서울숲 새벽산책', 37.5444, 127.0374, '아침 공기가 너무 좋아요', '2026-05-06 00:00:00', 'SEOUL', 'HEALING', 0.1, 40, 2);
INSERT INTO post (title, latitude, longitude, content, created_at, region, category, congestion, like_num, writer_id) VALUES ('부산 흰여울마을', 35.0689, 129.0163, '골목 걷기 최고', '2026-05-07 00:00:00', 'BUSAN', 'HIDDEN_SPOT', 0.2, 15, 1);
INSERT INTO post (title, latitude, longitude, content, created_at, region, category, congestion, like_num, writer_id) VALUES ('제주 카카오농장', 33.3942, 126.2430, '조용하고 예쁜 곳', '2026-05-08 00:00:00', 'JEJU', 'CAFE', 0.3, 22, 2);
INSERT INTO post (title, latitude, longitude, content, created_at, region, category, congestion, like_num, writer_id) VALUES ('경복궁 야경', 37.5796, 126.9770, '밤에 혼자 걷기 좋음', '2026-05-09 00:00:00', 'SEOUL', 'NIGHT_VIEW', 0.3, 35, 1);
INSERT INTO post (title, latitude, longitude, content, created_at, region, category, congestion, like_num, writer_id) VALUES ('강릉 안목해변', 37.7669, 128.9582, '커피 한잔하며 바다 구경', '2026-05-10 00:00:00', 'GANGWON', 'CAFE', 0.2, 28, 2);

-- reliability (target: 1번 유저)
INSERT INTO reliability (reliability, rater, target, created_at) VALUES (4.5, 2, 1, '2026-05-01 00:00:00');
INSERT INTO reliability (reliability, rater, target, created_at) VALUES (3.8, 2, 1, '2026-05-02 00:00:00');
INSERT INTO reliability (reliability, rater, target, created_at) VALUES (4.2, 2, 1, '2026-05-03 00:00:00');
INSERT INTO reliability (reliability, rater, target, created_at) VALUES (5.0, 2, 1, '2026-05-04 00:00:00');
INSERT INTO reliability (reliability, rater, target, created_at) VALUES (3.5, 2, 1, '2026-05-05 00:00:00');

-- best_post
INSERT INTO best_post (post_id, created_at) VALUES (6, '2026-04-01 00:00:00');
INSERT INTO best_post (post_id, created_at) VALUES (4, '2026-03-01 00:00:00');
INSERT INTO best_post (post_id, created_at) VALUES (9, '2026-02-01 00:00:00');