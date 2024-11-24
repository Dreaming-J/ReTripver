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
        ('test','c081d2d4c53f1ae185779aa47f344413833c143e9dc00b8cb404e0dc386628474d317e94eb62ebe74b3cdb2e25fb30839a2fa88664710dd36b9d967fdbb1ac8b','67e07ac2430420e0ce57c136868c1626','김테스트','test@test.com','https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/8d259f70bce34ad7b05bcadf686b183a.webp','안녕하세요',53,'achievement_visit',1,'2024-11-01 05:13:20',2);
INSERT INTO `follow`
VALUES	('test','guest1'),
		('guest1','test'),
        ('user','test'),
        ('test','user');
INSERT INTO `visit_counts`
VALUES	('test',1,5),
		('test',36,1);
INSERT INTO `notifications`
VALUES	(1,'test','알림이 왔습니다.',0),
		(2,'test','읽은 알림',1);

-- -----------------------------------
-- 여행 관련 TABLE
-- -----------------------------------

INSERT INTO `plans`
VALUES	(1,'test','신나는 부산 여행!',6,0,1),
		(2,'test','설렘 가득 제주도 여행!',39,0,0),
		(3,'test','한적한 강릉 바다 힐링 여행',32,0,1),
		(4,'test','가을빛 담은 경주 역사 탐방',35,0,0),
		(5,'test','푸른 물결 속 여수 밤바다 여행',38,0,1),
		(6,'test','서울에서의 활기찬 문화 체험',1,0,1),
		(7,'test','속초 설악산 등반과 힐링 투어',32,0,1),
		(8,'test','대구 별빛 야경 투어',4,0,1),
		(9,'test','전주의 한옥마을과 전통 맛집 탐방',37,0,0),
		(10,'test','고요한 남해의 섬마을 여행',36,0,1),
		(11,'test','울산 태화강과 대왕암 공원 산책',7,0,1);
INSERT INTO `courses`
VALUES	(1,6,3837,0,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png','http://tong.visitkorea.or.kr/cms/resource/33/2678633_image2_1.jpg',10,1),
		(2,6,4486,1,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png','http://tong.visitkorea.or.kr/cms/resource/03/3092503_image2_1.jpg',10,0),
		(3,6,3931,2,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png','https://encrypted-tbn3.gstatic.com/licensed-image?q=tbn:ANd9GcSYsgkE8xHZLrfG3lL_4rj7nAmBudaK75f2D1bSS7Vjkjd8mCyYMPwCoelTvFAFPaaqlufctrTyiV4vAH-fBqAtR_D6xkQDKsu9cBikkQ',10,0);
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
VALUES	('test',1),
		('test',4);
INSERT INTO `achievement_tier`
VALUES	(1,'브론즈 업적','브론즈 달성 시 얻을 수 있는 업적',1,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png'),
		(2,'실버 업적','실버 달성 시 얻을 수 있는 업적',2,'https://cdn4.iconfinder.com/data/icons/solid-part-6/128/image_icon-512.png');
INSERT INTO `acquire_tier`
VALUES	('test',1);