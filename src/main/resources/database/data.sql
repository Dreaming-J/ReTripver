USE `you1000`;

-- -----------------------------------
-- 유저 관련 TABLE
-- -----------------------------------

INSERT INTO `tier`(`id`, `name`, `img`, `base_exp`)
VALUES	(1,'브론즈','https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/tier/tier-bronze.png',0),
		(2,'실버','https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/tier/tier-silver.png',100),
        (3,'골드','https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/tier/tier-gold.png',200),
        (4,'플레티넘','https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/tier/tier-platinum.png',300),
        (5,'다이아','https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/tier/tier-diamond.png',400);
INSERT INTO `users`
VALUES	('admin','74871D696745843B5119B3B72F115C44BA49068329E482F98C76D589615C32F1667ACFF996960DA433FD85B8BCA741C6B78F84A9D4E4E50404FFC82C37550DC0','67e07ac2430420e0ce57c136868c1626','관리자','admin@admin.com','https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png','사이트 관리자입니다.',999,NULL,NULL,'2024-11-01 05:13:20',5),
		('guest1','DA63F86849923626240CA014974CC8EC4DAB2E10AAAF619ADEB99C19C31BFFF4568C440C7C299D49D8EACF6EDDB76551BF8AFDCBFF5663A44532414B2E83E250','67e07ac2430420e0ce57c136868c1626','이방문자','guest1@guest.com','https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png','방문자입니다.',150,NULL,NULL,'2024-11-01 05:13:20',2),
        ('pro1','F77F231FECFC90464F2DF4421E76C5DB6CA92C114291C5C9D74D65D755F3F2AB2431687D8FC9DAD776B94DE20C89536BD7334AF0302B6AAFEF139D7FDC24D807','67e07ac2430420e0ce57c136868c1626','정전문가','pro1@pro.com','https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png','전문가 수준의 유저입니다.',540,NULL,NULL,'2024-11-01 05:13:20',5),
        ('user','5FAF24FFACD18F7B42D122B6C217E8A8FD86F0F2B9B8831DFCE67ECF848CD5F27445873237F38D24CD1DDB3B7E593F3D4CA39DE5A0627FD37423022EF7B69809','67e07ac2430420e0ce57c136868c1626','박유저','user@user.com','https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png','유저입니다.',250,NULL,NULL,'2024-11-01 05:13:20',3),
        ('kim_JJong','c081d2d4c53f1ae185779aa47f344413833c143e9dc00b8cb404e0dc386628474d317e94eb62ebe74b3cdb2e25fb30839a2fa88664710dd36b9d967fdbb1ac8b','67e07ac2430420e0ce57c136868c1626','김테스트','kim_JJong@kim_JJong.com','https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/8d259f70bce34ad7b05bcadf686b183a.webp','여행을 좋아하는 27살 ㅁㅁ입니다.\n잘 부탁드립니다.\n강원도 좋아',157,'achievement_visit',1,'2024-11-01 05:13:20',4);
INSERT INTO `follow`
VALUES	('kim_JJong','guest1'),
		('guest1','kim_JJong'),
        ('user','kim_JJong'),
        ('kim_JJong','user');
INSERT INTO `visit_counts`
VALUES	('kim_JJong',1,5),
		('kim_JJong',36,1);
INSERT INTO `notifications`
VALUES	(1,'kim_JJong','알림이 왔습니다.',0),
		(2,'kim_JJong','읽은 알림',1);

-- -----------------------------------
-- 여행 관련 TABLE
-- -----------------------------------

-- 서울(1) - 7개 (5개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 1, '남산타워 야경보기', true, true),
(0, 'kim_JJong', 1, '경복궁 고궁투어', true, true),
(0, 'kim_JJong', 1, '홍대 거리공연 구경', false, true),
(0, 'kim_JJong', 1, '북촌한옥마을 산책', true, true),
(0, 'kim_JJong', 1, '롯데월드 놀이공원', true, true),
(0,'kim_JJong',1,'서울에서의 활기찬 문화 체험', false, true),
(0, 'kim_JJong', 1, '창덕궁 후원 산책', true, true);

-- 인천(2) - 3개 (2개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 2, '송도 센트럴파크 산책', true, true),
(0, 'kim_JJong', 2, '월미도 문화의거리', false, true),
(0, 'kim_JJong', 2, '차이나타운 맛집탐방', true, true);

-- 대전(3) - 2개 (1개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 3, '대전 엑스포 과학공원 방문', false, true),
(0, 'kim_JJong', 3, '유성온천 족욕체험', true, true);

-- 대구(4) - 4개 (3개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 4, '팔공산 등산', true, true),
(0, 'kim_JJong', 4, '서문시장 야시장', false, true),
(0, 'kim_JJong', 4, '김광석 거리 방문', true, true),
(0, 'kim_JJong', 4, '이월드 놀이공원', true, true);

-- 광주(5) - 4개 (3개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 5, '무등산 등산', true, true),
(0, 'kim_JJong', 5, '국립아시아문화전당 관람', true, true),
(0, 'kim_JJong', 5, '충장로 쇼핑', false, true),
(0, 'kim_JJong', 5, '사직공원 산책', true, true);

-- 부산(6) - 8개 (6개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 6, '해운대 해수욕장', true, true),
(0, 'kim_JJong', 6, '광안리 불꽃축제', true, true),
(0, 'kim_JJong', 6, '감천문화마을 관광', false, true),
(0, 'kim_JJong', 6, '자갈치시장 구경', true, true),
(0, 'kim_JJong', 6, '태종대 드라이브', true, true),
(0, 'kim_JJong', 6, '남포동 BIFF거리', true, true),
(0, 'kim_JJong', 6, '송정해수욕장 서핑', false, true),
(0, 'kim_JJong', 6, '영도대교 야경', true, true);

-- 울산(7) - 3개 (2개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 7, '간절곶 일출', false, true),
(0, 'kim_JJong', 7, '대왕암공원 산책', true, true),
(0, 'kim_JJong', 7, '태화강 십리대숲', true, true);

-- 세종(8) - 2개 (1개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 8, '세종호수공원 산책', true, true),
(0, 'kim_JJong', 8, '국책연구단지 투어', false, true);

-- 경기도(31) - 6개 (4개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 31, '에버랜드 놀이공원', true, true),
(0, 'kim_JJong', 31, '아침고요수목원', false, true),
(0, 'kim_JJong', 31, '화성행궁 관람', true, true),
(0, 'kim_JJong', 31, '한국민속촌 방문', false, true),
(0, 'kim_JJong', 31, '광명동굴 체험', true, true),
(0, 'kim_JJong', 31, '헤이리 예술마을', true, true);

-- 강원도(32) - 9개 (6개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 32, '속초 설악산', true, true),
(0, 'kim_JJong', 32, '강릉 커피거리', true, true),
(0, 'kim_JJong', 32, '춘천 닭갈비거리', false, true),
(0, 'kim_JJong', 32, '남이섬 관광', true, true),
(0, 'kim_JJong', 32, '정동진 일출', false, true),
(0, 'kim_JJong', 32, '양양 서핑', true, true),
(0, 'kim_JJong', 32, '원주 치악산', false, true),
(0, 'kim_JJong', 32, '평창 스키장', true, true),
(0, 'kim_JJong', 32, '강릉 경포대', true, true);

-- 충청북도(33) - 0개
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES (0, 'user', 32, '속초 설악산', false, true);

-- 충청남도(34) - 5개 (4개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 34, '태안 만리포해수욕장', true, true),
(0, 'kim_JJong', 34, '공주 공산성', true, true),
(0, 'kim_JJong', 34, '부여 궁남지', false, true),
(0, 'kim_JJong', 34, '천안 독립기념관', true, true),
(0, 'kim_JJong', 34, '아산 외암민속마을', true, true);

-- 경상북도(35) - 6개 (4개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 35, '경주 불국사', true, true),
(0, 'kim_JJong', 35, '포항 호미곶', true, true),
(0, 'kim_JJong', 35, '안동 하회마을', false, true),
(0, 'kim_JJong', 35, '울릉도 관광', true, true),
(0, 'kim_JJong', 35, '독도 방문', false, true),
(0, 'kim_JJong', 35, '문경새재 도보여행', true, true);

-- 경상남도(36) - 7개 (5개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 36, '통영 케이블카', true, true),
(0, 'kim_JJong', 36, '거제 바람의언덕', true, true),
(0, 'kim_JJong', 36, '진주성 야경', false, true),
(0, 'kim_JJong', 36, '남해 독일마을', true, true),
(0, 'kim_JJong', 36, '미륵산 드라이브', false, true),
(0, 'kim_JJong', 36, '외도 보타니아', true, true),
(0, 'kim_JJong', 36, '창원 진해군항제', true, true);

-- 전라북도(37) - 5개 (4개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 37, '전주 한옥마을', true, true),
(0, 'kim_JJong', 37, '부안 채석강', true, true),
(0, 'kim_JJong', 37, '군산 근대문화거리', true, true),
(0, 'kim_JJong', 37, '무주 덕유산리조트', false, true),
(0, 'kim_JJong', 37, '남원 광한루', true, true);

-- 전라남도(38) - 8개 (6개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 38, '여수 해상케이블카', true, true),
(0, 'kim_JJong', 38, '순천만 국가정원', true, true),
(0, 'kim_JJong', 38, '보성 녹차밭', false, true),
(0, 'kim_JJong', 38, '담양 죽녹원', true, true),
(0, 'kim_JJong', 38, '목포 해상케이블카', true, true),
(0, 'kim_JJong', 38, '완도 청산도', false, true),
(0, 'kim_JJong', 38, '신안 퍼플섬', true, true),
(0, 'kim_JJong', 38, '해남 땅끝마을', true, true);

-- 제주도(39) - 10개 (7개 true)
INSERT INTO plans (id, user_id, sido_code, title, is_clear, is_public)
VALUES 
(0, 'kim_JJong', 39, '성산일출봉 일출', true, true),
(0, 'kim_JJong', 39, '만장굴 탐험', true, true),
(0, 'kim_JJong', 39, '우도 관광', false, true),
(0, 'kim_JJong', 39, '한라산 등반', true, true),
(0, 'kim_JJong', 39, '중문관광단지', true, true),
(0, 'kim_JJong', 39, '섭지코지 산책', false, true),
(0, 'kim_JJong', 39, '협재해수욕장', true, true),
(0, 'kim_JJong', 39, '카멜리아힐', false, true),
(0, 'kim_JJong', 39, '천지연폭포', true, true),
(0, 'kim_JJong', 39, '용두암', true, true);

-- 남산타워 야경보기 관련 코스
INSERT INTO courses (id, plan_id, attraction_no, course_order, mission_img, user_img, exp, is_clear) VALUES 
(0, 1, 4885, 0, 'http://tong.visitkorea.or.kr/cms/resource/92/1570392_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/92/1570392_image2_1.jpg', 100, false),
(0, 1, 11556, 1, 'http://tong.visitkorea.or.kr/cms/resource/93/2790093_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/93/2790093_image2_1.jpg', 150, false),
(0, 1, 3933, 2, 'http://tong.visitkorea.or.kr/cms/resource/94/2796194_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/94/2796194_image2_1.jpg', 100, false);

-- 경복궁 고궁투어 관련 코스
INSERT INTO courses (id, plan_id, attraction_no, course_order, mission_img, user_img, exp, is_clear) VALUES 
(0, 2, 3837, 0, 'http://tong.visitkorea.or.kr/cms/resource/33/2678633_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/33/2678633_image2_1.jpg', 100, false),
(0, 2, 5262, 1, 'http://tong.visitkorea.or.kr/cms/resource/24/3349624_image2_1.png', 'http://tong.visitkorea.or.kr/cms/resource/24/3349624_image2_1.png', 150, false),
(0, 2, 5263, 2, 'http://tong.visitkorea.or.kr/cms/resource/99/2962999_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/99/2962999_image2_1.jpg', 200, false),
(0, 2, 5587, 3, 'http://tong.visitkorea.or.kr/cms/resource/40/1570340_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/40/1570340_image2_1.jpg', 150, false);

-- 홍대 거리공연 구경 관련 코스
INSERT INTO courses (id, plan_id, attraction_no, course_order, mission_img, user_img, exp, is_clear) VALUES 
(0, 3, 4325, 0, 'http://tong.visitkorea.or.kr/cms/resource/95/3082395_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/95/3082395_image2_1.jpg', 100, false),
(0, 3, 4580, 1, 'http://tong.visitkorea.or.kr/cms/resource/99/3082399_image2_1.JPG', 'http://tong.visitkorea.or.kr/cms/resource/99/3082399_image2_1.JPG', 150, false),
(0, 3, 9995, 2, 'http://tong.visitkorea.or.kr/cms/resource/05/2875505_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/05/2875505_image2_1.jpg', 100, false);

-- 북촌한옥마을 산책 관련 코스
INSERT INTO courses (id, plan_id, attraction_no, course_order, mission_img, user_img, exp, is_clear) VALUES 
(0, 4, 4110, 0, 'http://tong.visitkorea.or.kr/cms/resource/04/3304404_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/04/3304404_image2_1.jpg', 100, false),
(0, 4, 4852, 1, 'http://tong.visitkorea.or.kr/cms/resource/16/3303716_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/16/3303716_image2_1.jpg', 150, false),
(0, 4, 10887, 2, 'http://tong.visitkorea.or.kr/cms/resource/06/2654806_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/06/2654806_image2_1.jpg', 200, false),
(0, 4, 5883, 3, 'http://tong.visitkorea.or.kr/cms/resource/59/3069959_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/59/3069959_image2_1.jpg', 150, false);

-- 롯데월드 놀이공원 관련 코스
INSERT INTO courses (id, plan_id, attraction_no, course_order, mission_img, user_img, exp, is_clear) VALUES 
(0, 5, 4801, 0, 'http://tong.visitkorea.or.kr/cms/resource/45/2384845_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/45/2384845_image2_1.jpg', 100, false),
(0, 5, 4006, 1, 'http://tong.visitkorea.or.kr/cms/resource/00/3304300_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/00/3304300_image2_1.jpg', 200, false),
(0, 5, 6944, 2, 'http://tong.visitkorea.or.kr/cms/resource/27/2878927_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/27/2878927_image2_1.jpg', 150, false);

-- 서울에서의 활기찬 문화 체험 관련 코스
INSERT INTO courses (id, plan_id, attraction_no, course_order, mission_img, user_img, exp, is_clear) VALUES 
(0, 6, 3837, 0, 'http://tong.visitkorea.or.kr/cms/resource/33/2678633_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/33/2678633_image2_1.jpg', 10, true),
(0, 6, 4486, 1, 'http://tong.visitkorea.or.kr/cms/resource/03/3092503_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/03/3092503_image2_1.jpg', 10, false),
(0, 6, 3931, 2, 'https://encrypted-tbn3.gstatic.com/licensed-image?q=tbn:ANd9GcSYsgkE8xHZLrfG3lL_4rj7nAmBudaK75f2D1bSS7Vjkjd8mCyYMPwCoelTvFAFPaaqlufctrTyiV4vAH-fBqAtR_D6xkQDKsu9cBikkQ', 'https://encrypted-tbn3.gstatic.com/licensed-image?q=tbn:ANd9GcSYsgkE8xHZLrfG3lL_4rj7nAmBudaK75f2D1bSS7Vjkjd8mCyYMPwCoelTvFAFPaaqlufctrTyiV4vAH-fBqAtR_D6xkQDKsu9cBikkQ', 10, false);

-- 창덕궁 후원 산책 관련 코스
INSERT INTO courses (id, plan_id, attraction_no, course_order, mission_img, user_img, exp, is_clear) VALUES 
(0, 7, 4484, 0, 'http://tong.visitkorea.or.kr/cms/resource/01/1568301_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/01/1568301_image2_1.jpg', 100, false),
(0, 7, 4485, 1, 'http://tong.visitkorea.or.kr/cms/resource/47/2031847_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/47/2031847_image2_1.jpg', 200, false),
(0, 7, 5458, 2, 'http://tong.visitkorea.or.kr/cms/resource/50/3109550_image2_1.jpg', 'http://tong.visitkorea.or.kr/cms/resource/50/3109550_image2_1.jpg', 150, false);
        
INSERT INTO `plan_like`
VALUES	(6,'admin'),
		(8,'admin'),
		(1,'pro1'),
		(6,'pro1'),
		(1,'user'),
		(2,'user'),
		(3,'user'),
		(6,'user');

-- -----------------------------------
-- 업적 관련 TABLE
-- -----------------------------------

INSERT INTO `achievement_visit`
VALUES	(1,'서울 1회','서울을 1회 이상 방문하여 얻을 수 있는 업적',1,1,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png'),
		(2,'서울 3회','서울을 3회 이상 방문하여 얻을 수 있는 업적',1,3,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png'),
		(3,'서울 5회','서울을 5회 이상 방문하여 얻을 수 있는 업적',1,5,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png'),
		(4,'경남 수호자','경상남도를 3회 이상 방문하여 얻을 수 있는 업적',36,3,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png');
INSERT INTO `acquire_visit`
VALUES	('kim_JJong',1),
		('kim_JJong',4);
INSERT INTO `achievement_tier`
VALUES	(1,'브론즈 업적','브론즈 달성 시 얻을 수 있는 업적',1,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png'),
		(2,'실버 업적','실버 달성 시 얻을 수 있는 업적',2,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png');
INSERT INTO `acquire_tier`
VALUES	('kim_JJong',1);