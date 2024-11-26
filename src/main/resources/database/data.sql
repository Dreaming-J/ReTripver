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
VALUES	('scuba_lover','74871D696745843B5119B3B72F115C44BA49068329E482F98C76D589615C32F1667ACFF996960DA433FD85B8BCA741C6B78F84A9D4E4E50404FFC82C37550DC0','67e07ac2430420e0ce57c136868c1626','관리자','scuba_lover@scuba_lover.com','https://media-cdn.tripadvisor.com/media/photo-s/16/f9/42/7c/caption.jpg','다이빙 좋아~',999,'achievement_visit',28,'2024-11-01 05:13:20',5),
		('luckybicky_ni_city','DA63F86849923626240CA014974CC8EC4DAB2E10AAAF619ADEB99C19C31BFFF4568C440C7C299D49D8EACF6EDDB76551BF8AFDCBFF5663A44532414B2E83E250','67e07ac2430420e0ce57c136868c1626','이방문자','luckybicky_ni_city@guest.com','https://health.chosun.com/site/data/img_dir/2024/08/02/2024080201756_0.jpg','럭키비키니시티잖아~',150,'achievement_visit',24,'2024-11-01 05:13:20',2),
        ('yoon_pro','F77F231FECFC90464F2DF4421E76C5DB6CA92C114291C5C9D74D65D755F3F2AB2431687D8FC9DAD776B94DE20C89536BD7334AF0302B6AAFEF139D7FDC24D807','67e07ac2430420e0ce57c136868c1626','정전문가','yoon_pro@pro.com','https://watermark.lovepik.com/photo/20211210/large/lovepik-golfers-back-picture_501801971.jpg','골프를 사랑하는 40대.\n마음만은 아직 20대!',540,'achievement_visit',1,'2024-11-01 05:13:20',5),
        ('no_jjaem_man','5FAF24FFACD18F7B42D122B6C217E8A8FD86F0F2B9B8831DFCE67ECF848CD5F27445873237F38D24CD1DDB3B7E593F3D4CA39DE5A0627FD37423022EF7B69809','67e07ac2430420e0ce57c136868c1626','박유저','no_jjaem_man@no_jjaem_man.com','https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/%EB%85%B8%EC%9E%BC.png','노잼도시 대전을 사랑한 남자',250,'achievement_visit',12,'2024-11-01 05:13:20',3),
        ('kim_JJong','c081d2d4c53f1ae185779aa47f344413833c143e9dc00b8cb404e0dc386628474d317e94eb62ebe74b3cdb2e25fb30839a2fa88664710dd36b9d967fdbb1ac8b','67e07ac2430420e0ce57c136868c1626','김테스트','kim_JJong@kim_JJong.com','https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/8d259f70bce34ad7b05bcadf686b183a.webp','여행을 좋아하는 27살 취준생입니다.\n잘 부탁드립니다.\n강릉 좋아',157,'achievement_visit',35,'2024-11-01 05:13:20',4);
INSERT INTO `follow`
VALUES	('kim_JJong','luckybicky_ni_city'),
		('luckybicky_ni_city','kim_JJong'),
        ('no_jjaem_man','kim_JJong'),
        ('kim_JJong','no_jjaem_man');
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
VALUES (0, 'no_jjaem_man', 32, '속초 설악산', false, true);

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

-- 대전 코스
INSERT INTO courses (id, plan_id, attraction_no, course_order, mission_img, user_img, exp, is_clear) VALUES 
(0, 11, 14664, 0, 'http://tong.visitkorea.or.kr/cms/resource/00/2760700_image2_1.jpg', NULL, 100, false),
(0, 11, 12781, 1, 'http://tong.visitkorea.or.kr/cms/resource/23/3338823_image2_1.jpg', NULL, 150, false),
(0, 11, 14782, 2, 'http://tong.visitkorea.or.kr/cms/resource/94/3023994_image2_1.jpg', NULL, 100, false),
(0, 11, 14709, 3, 'http://tong.visitkorea.or.kr/cms/resource/05/2794105_image2_1.jpg', NULL, 120, false);

INSERT INTO current_plans
VALUES ('kim_JJong', 11);
        
INSERT INTO `plan_like`
VALUES	(6,'scuba_lover'),
		(8,'scuba_lover'),
		(1,'yoon_pro'),
		(6,'yoon_pro'),
		(1,'no_jjaem_man'),
		(2,'no_jjaem_man'),
		(3,'no_jjaem_man'),
		(6,'no_jjaem_man');

-- -----------------------------------
-- 업적 관련 TABLE
-- -----------------------------------
INSERT INTO achievement_visit (id, name, content, sido_code, visit_count, img) VALUES
(0, '[서울] 초보 모험가', '서울을 1회 방문한 모험가에게 주어지는 칭호입니다.', 1, 1, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-1-1.png'),
(0, '[서울] 주니어 모험가', '서울을 3회 방문한 모험가에게 주어지는 칭호입니다.', 1, 3, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-1-2.png'),
(0, '[서울] 베테랑 모험가', '서울을 5회 방문한 모험가에게 주어지는 칭호입니다.', 1, 5, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-1-3.png'),
(0, '[서울] 마스터 모험가', '서울을 7회 방문한 모험가에게 주어지는 칭호입니다.', 1, 7, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-1-4.png'),
(0, '[서울] 정복자', '서울을 10회 방문한 모험가에게 주어지는 칭호입니다.', 1, 10, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-1-5.png'),

(0, '[인천] 초보 모험가', '인천을 1회 방문한 모험가에게 주어지는 칭호입니다.', 2, 1, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-2-1.png'),
(0, '[인천] 주니어 모험가', '인천을 3회 방문한 모험가에게 주어지는 칭호입니다.', 2, 3, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-2-2.png'),
(0, '[인천] 베테랑 모험가', '인천을 5회 방문한 모험가에게 주어지는 칭호입니다.', 2, 5, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-3-3.png'),
(0, '[인천] 마스터 모험가', '인천을 7회 방문한 모험가에게 주어지는 칭호입니다.', 2, 7, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-3-4.png'),
(0, '[인천] 정복자', '인천을 10회 방문한 모험가에게 주어지는 칭호입니다.', 2, 10, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-3-5.png'),

(0, '[대전] 초보 모험가', '대전을 1회 방문한 모험가에게 주어지는 칭호입니다.', 3, 1, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-3-1.png'),
(0, '[대전] 주니어 모험가', '대전을 3회 방문한 모험가에게 주어지는 칭호입니다.', 3, 3, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-3-2.png'),
(0, '[대전] 베테랑 모험가', '대전을 5회 방문한 모험가에게 주어지는 칭호입니다.', 3, 5, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-3-3.png'),
(0, '[대전] 마스터 모험가', '대전을 7회 방문한 모험가에게 주어지는 칭호입니다.', 3, 7, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-3-4.png'),
(0, '[대전] 정복자', '대전을 10회 방문한 모험가에게 주어지는 칭호입니다.', 3, 10, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-3-5png'),

(0, '[대구] 초보 모험가', '대구를 1회 방문한 모험가에게 주어지는 칭호입니다.', 4, 1, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-4-1.png'),
(0, '[대구] 주니어 모험가', '대구를 3회 방문한 모험가에게 주어지는 칭호입니다.', 4, 3, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-4-2.png'),
(0, '[대구] 베테랑 모험가', '대구를 5회 방문한 모험가에게 주어지는 칭호입니다.', 4, 5, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-4-3.png'),
(0, '[대구] 마스터 모험가', '대구를 7회 방문한 모험가에게 주어지는 칭호입니다.', 4, 7, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-4-4.png'),
(0, '[대구] 정복자', '대구를 10회 방문한 모험가에게 주어지는 칭호입니다.', 4, 10, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-4-5.png'),

(0, '[광주] 초보 모험가', '광주를 1회 방문한 모험가에게 주어지는 칭호입니다.', 5, 1, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-5-1.png'),
(0, '[광주] 주니어 모험가', '광주를 3회 방문한 모험가에게 주어지는 칭호입니다.', 5, 3, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-5-2.png'),
(0, '[광주] 베테랑 모험가', '광주를 5회 방문한 모험가에게 주어지는 칭호입니다.', 5, 5, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-5-3.png'),
(0, '[광주] 마스터 모험가', '광주를 7회 방문한 모험가에게 주어지는 칭호입니다.', 5, 7, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-5-4.png'),
(0, '[광주] 정복자', '광주를 10회 방문한 모험가에게 주어지는 칭호입니다.', 5, 10, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-5-5.png'),

(0, '[부산] 초보 모험가', '부산을 1회 방문한 모험가에게 주어지는 칭호입니다.', 6, 1, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-6-1.png'),
(0, '[부산] 주니어 모험가', '부산을 3회 방문한 모험가에게 주어지는 칭호입니다.', 6, 3, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-6-2.png'),
(0, '[부산] 베테랑 모험가', '부산을 5회 방문한 모험가에게 주어지는 칭호입니다.', 6, 5, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-6-3.png'),
(0, '[부산] 마스터 모험가', '부산을 7회 방문한 모험가에게 주어지는 칭호입니다.', 6, 7, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-6-4.png'),
(0, '[부산] 정복자', '부산을 10회 방문한 모험가에게 주어지는 칭호입니다.', 6, 10, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-6-5.png'),

(0, '[제주도] 초보 모험가', '제주도을 1회 방문한 모험가에게 주어지는 칭호입니다.', 7, 1, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-7-1.png'),
(0, '[제주도] 주니어 모험가', '제주도을 3회 방문한 모험가에게 주어지는 칭호입니다.', 7, 3, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-7-2.png'),
(0, '[제주도] 베테랑 모험가', '제주도을 5회 방문한 모험가에게 주어지는 칭호입니다.', 7, 5, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-7-3.png'),
(0, '[제주도] 마스터 모험가', '제주도을 7회 방문한 모험가에게 주어지는 칭호입니다.', 7, 7, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-7-4.png'),
(0, '[제주도] 정복자', '제주도을 10회 방문한 모험가에게 주어지는 칭호입니다.', 7, 10, 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/badges/badge-7-5.png');

INSERT INTO `acquire_visit`
VALUES	('kim_JJong',1),
		('kim_JJong',2),
		('kim_JJong',3),
		('kim_JJong',4),
		('kim_JJong',6),
		('kim_JJong',7),
		('kim_JJong',11),
		('kim_JJong',16),
		('kim_JJong',17),
		('kim_JJong',21),
		('kim_JJong',22),
		('kim_JJong',26),
		('kim_JJong',27),
		('kim_JJong',28),
		('kim_JJong',29),
		('kim_JJong',31),
		('kim_JJong',32),
		('kim_JJong',33),
		('kim_JJong',34),
		('kim_JJong',35);
        
INSERT INTO `achievement_tier`
VALUES	(1,'브론즈 업적','브론즈 달성 시 얻을 수 있는 업적',1,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png'),
		(2,'실버 업적','실버 달성 시 얻을 수 있는 업적',2,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png');
        
INSERT INTO `acquire_tier`
VALUES	('kim_JJong',1);