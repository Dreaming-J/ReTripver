DROP DATABASE IF EXISTS `you1000`;
CREATE DATABASE `you1000`;
USE `you1000`;

-- -----------------------------------
-- 장소 관련 TABLE
-- -----------------------------------

CREATE TABLE `sidos` (
    `no`		INT NOT NULL AUTO_INCREMENT,
    `sido_code`	INT NOT NULL UNIQUE,
    `sido_name`	VARCHAR(20) NOT NULL,
    
    PRIMARY KEY (`no`),
    INDEX (`sido_code` ASC) VISIBLE
);

CREATE TABLE `guguns` (
    `no`			INT NOT NULL AUTO_INCREMENT,
    `sido_code`		INT DEFAULT NULL,
    `gugun_code`	INT DEFAULT NULL,
    `gugun_name`	VARCHAR(20) DEFAULT NULL,
    
    PRIMARY KEY (`no`),
	INDEX (`sido_code` ASC) VISIBLE,
	INDEX (`gugun_code` ASC) VISIBLE,
    FOREIGN KEY (`sido_code`) REFERENCES `sidos` (`sido_code`)
);

CREATE TABLE `contenttypes` (
    `content_type_id`	INT NOT NULL AUTO_INCREMENT,
    `content_type_name`	VARCHAR(45) NOT NULL,
    
    PRIMARY KEY (`content_type_id`)
);

CREATE TABLE `attractions` (
    `no`				INT NOT NULL AUTO_INCREMENT,
    `content_id`		INT DEFAULT NULL,
    `title`				VARCHAR(500) DEFAULT NULL,
    `content_type_id`	INT DEFAULT NULL,
    `area_code`			INT DEFAULT NULL,
    `si_gun_gu_code`	INT DEFAULT NULL,
    `first_image1`		VARCHAR(255) DEFAULT NULL,
    `first_image2`		VARCHAR(255) DEFAULT NULL,
    `map_lavel`			INT DEFAULT NULL,
    `latitude`			DECIMAL(20, 17) DEFAULT NULL,
    `longitude`			DECIMAL(20, 17) DEFAULT NULL,
    `tel`				VARCHAR(20) DEFAULT NULL,
    `addr1`				VARCHAR(100) DEFAULT NULL,
    `addr2`				VARCHAR(100) DEFAULT NULL,
    `homepage`			VARCHAR(1000) DEFAULT NULL,
    `overview`			VARCHAR(10000) DEFAULT NULL,
    
    PRIMARY KEY (`no`),
    FOREIGN KEY (`area_code`) REFERENCES `sidos` (`sido_code`),
    FOREIGN KEY (`si_gun_gu_code`) REFERENCES `guguns` (`gugun_code`),
    FOREIGN KEY (`content_type_id`) REFERENCES `contenttypes` (`content_type_id`),
	INDEX (`content_type_id` ASC) VISIBLE,
	INDEX (`area_code` ASC) VISIBLE,
	INDEX (`si_gun_gu_code` ASC) VISIBLE
);

-- -----------------------------------
-- 유저 관련 TABLE
-- -----------------------------------

CREATE TABLE `tier` (
    `id`		INT NOT NULL AUTO_INCREMENT,
    `name`		VARCHAR(20) NOT NULL,
    `img`		VARCHAR(255) NOT NULL,
    `base_exp`	INT NOT NULL DEFAULT 0,
    
    PRIMARY KEY (`id`)
);

CREATE TABLE `users` (
    `id`				VARCHAR(16) NOT NULL,
    `password`			VARCHAR(20) NOT NULL,
    `name`				VARCHAR(16) NOT NULL,
    `email`				VARCHAR(100) NOT NULL,
    `profile_img`		VARCHAR(1000) DEFAULT NULL,
    `profile_desc`		VARCHAR(1000) DEFAULT NULL,
    `exp`				INT NOT NULL DEFAULT 0,
    `achievement_table`	VARCHAR(100) DEFAULT NULL,
    `achievement_id`	INT DEFAULT NULL,
    `register_time`		TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `tier_no`			INT NOT NULL DEFAULT 1,
    
    PRIMARY KEY (`id`),
    FOREIGN KEY (`tier_no`) REFERENCES `tier`(`id`)
);

CREATE TABLE `follow` (
    `from_id`	VARCHAR(16) NOT NULL,
    `to_id`		VARCHAR(16) NOT NULL,
    
    PRIMARY KEY (`from_id`, `to_id`),
    FOREIGN KEY (`from_id`) REFERENCES `users`(`id`),
    FOREIGN KEY (`to_id`) REFERENCES `users`(`id`)
);

CREATE TABLE `visit_counts` (
    `user_id`	VARCHAR(16) NOT NULL,
    `sido_code`	INT NOT NULL,
    `count`		INT NOT NULL DEFAULT 0,
    
    PRIMARY KEY (`user_id`, `sido_code`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    FOREIGN KEY (`sido_code`) REFERENCES `sidos`(`sido_code`)
);

CREATE TABLE `notifications` (
    `id`		INT NOT NULL AUTO_INCREMENT,
    `user_id`	VARCHAR(16) NOT NULL,
    `message`	VARCHAR(1000) NOT NULL,
    `isRead`	BOOLEAN NOT NULL DEFAULT false,
    
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);

-- -----------------------------------
-- 여행 관련 TABLE
-- -----------------------------------

CREATE TABLE `plans` (
    `id`		INT NOT NULL AUTO_INCREMENT,
    `user_id`	VARCHAR(16) NOT NULL,
    `title`		VARCHAR(100) NOT NULL,
    `sido_code`	INT NOT NULL,
    `is_clear`	BOOLEAN NOT NULL DEFAULT false,
    `is_public`	BOOLEAN NOT NULL DEFAULT true,
    
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    FOREIGN KEY (`sido_code`) REFERENCES `sidos`(`sido_code`)
);

CREATE TABLE `current_plans` (
    `user_id`	VARCHAR(16) NOT NULL,
    `plan_id`	INT NOT NULL,
    
    PRIMARY KEY (`user_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    FOREIGN KEY (`plan_id`) REFERENCES `plans`(`id`)
);

CREATE TABLE `courses` (
    `id`			INT NOT NULL AUTO_INCREMENT,
    `plan_id`		INT NOT NULL,
    `attraction_no`	INT NOT NULL,
    `course_order`	INT NOT NULL DEFAULT 0,
    `mission_img`	VARCHAR(255) NOT NULL,
    `user_img`		VARCHAR(255) DEFAULT NULL,
    `exp`			INT NOT NULL DEFAULT 0,
    `is_clear`		BOOLEAN NOT NULL DEFAULT false,
    
    PRIMARY KEY (`id`),
    FOREIGN KEY (`plan_id`) REFERENCES `plans`(`id`),
    FOREIGN KEY (`attraction_no`) REFERENCES `attractions`(`no`)
);

CREATE TABLE `plan_like` (
    `plan_id`	INT NOT NULL,
    `user_id`	VARCHAR(16) NOT NULL,
    
    PRIMARY KEY (`plan_id`, `user_id`),
    FOREIGN KEY (`plan_id`) REFERENCES `plans`(`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);

-- -----------------------------------
-- 업적 관련 TABLE
-- -----------------------------------

CREATE TABLE `achievement_visit` (
    `id`			INT NOT NULL AUTO_INCREMENT,
    `name`			VARCHAR(100) NOT NULL,
    `content`		VARCHAR(100) NOT NULL,
    `sido_code`		INT NOT NULL,
    `visit_count`	INT NOT NULL,
    `img`			VARCHAR(255) NOT NULL,
    
    PRIMARY KEY (`id`),
    FOREIGN KEY (`sido_code`) REFERENCES `sidos`(`sido_code`)
);

CREATE TABLE `acquire_visit` (
    `user_id`			VARCHAR(16) NOT NULL,
    `achievement_id`	INT NOT NULL,
    
    PRIMARY KEY (`user_id`, `achievement_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    FOREIGN KEY (`achievement_id`) REFERENCES `achievement_visit`(`id`)
);

CREATE TABLE `achievement_tier` (
    `id`		INT NOT NULL AUTO_INCREMENT,
    `name`		VARCHAR(100) NOT NULL,
    `content`	VARCHAR(100) NOT NULL,
    `tier_no`	INT NOT NULL,
    `img`		VARCHAR(255) NOT NULL,
    
    PRIMARY KEY (`id`),
    FOREIGN KEY (`tier_no`) REFERENCES `tier`(`id`)
);

CREATE TABLE `acquire_tier` (
    `user_id`			VARCHAR(16) NOT NULL,
    `achievement_id`	INT NOT NULL,
    
    PRIMARY KEY (`user_id`, `achievement_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    FOREIGN KEY (`achievement_id`) REFERENCES `achievement_tier`(`id`)
);

-- -----------------------------------
-- 이메일 인증 관련 TABLE
-- -----------------------------------

CREATE TABLE `email_auth` (
    `email`			VARCHAR(16) NOT NULL,
    `code`			VARCHAR(16) NOT NULL,
    `complete_time`		TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


