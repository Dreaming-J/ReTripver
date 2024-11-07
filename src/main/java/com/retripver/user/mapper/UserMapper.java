package com.retripver.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.retripver.user.dto.FollowCountResponse;
import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.PwdModifyRequest;
import com.retripver.user.dto.QuestRateResponse;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.TierInfoResponse;
import com.retripver.user.dto.UserAchievementTierResponse;
import com.retripver.user.dto.UserAchievementVisitResponse;
import com.retripver.user.dto.UserInfoResponse;
import com.retripver.user.dto.UserModifyRequest;
import com.retripver.user.dto.UserProfileRequest;
import com.retripver.user.dto.UserSearchIdRequest;
import com.retripver.user.repository.UserSqlProvider;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM users WHERE id = #{id} AND password = #{password}")
	LoginResponse selectByIdAndPassword(LoginRequest loginRequest);

	@Insert("INSERT INTO users (id, password, name, email) VALUES (#{id}, #{password}, #{name}, #{email})")
	void insert(SignupRequest signupRequest);
	
	@Select("SELECT count(*) FROM users WHERE id = #{id}")
	int selectCountById(String id);

	@Update("UPDATE users SET profile_img = #{profileImg}, profile_desc = #{profileDesc} WHERE id = #{id}")
	void updateProfile(UserProfileRequest userProfileRequest);

	@Select("SELECT id FROM users WHERE name = #{name} AND email = #{email}")
	String selectByNameAndEmail(UserSearchIdRequest userSearchIdRequest);

	@Update("UPDATE users SET name = #{name}, email = #{email}, profile_img = #{profileImg}, profile_desc = #{profileDesc} WHERE id = #{id}")
	void update(UserModifyRequest userModifyRequest);

	@Update("UPDATE users SET password = #{newPassword} WHERE id = #{id}")
	void updatePassword(PwdModifyRequest pwdModifyRequset);

	@Delete("DELETE FROM users WHERE id = #{id}")
	void deleteUser(String id);

	@Select("SELECT count(*) FROM users WHERE from_id = #{fromId} AND to_id = #{toId}")
	int selectCountByFromIdAndToId(String fromId, String toId);

	@Delete("DELETE FROM usrs WHERE from_id = #{fromId} AND to_id = #{toId}")
	void deleteFollow(String fromId, String toId);

	@Insert("INSERT INTO follow VALUES (#{fromId}, #{toId})")
	void insertFollow(String fromId, String toId);
	
	@Select("""
			SELECT ct.id, ct.name, ct.img, nt.base_exp
			FROM tier ct LEFT JOIN tier nt
			ON (ct.id + 1) = nt.id
			WHERE ct.id = #{tierNo};
			""")
	@Results({
		@Result(property = "tierId", column = "id"),
		@Result(property = "tierName", column = "name"),
		@Result(property = "tierImg", column = "img"),
		@Result(property = "nextTierExp", column = "base_exp"),
	})
	TierInfoResponse selectTierById(int tierNo);
	
	@Select("SELECT * from users WHERE id=#{id}")
	@Results({
		@Result(property = "profileImg", column = "profile_img"),
		@Result(property = "profileDesc", column = "profile_desc"),
		@Result(property = "achievementTable", column = "achievement_table"),
		@Result(property = "achievementId", column = "achievement_id"),
		@Result(property = "tierInfo", column="tier_no", one = @One(select = "selectTierById"))
	})
	UserInfoResponse selectUserById(String id);
	
	@Select("""
			SELECT count(*) follower, following
			FROM follow ft
			JOIN (
				SELECT from_id, count(*) following
				FROM follow
				GROUP BY from_id
			) fm
			ON ft.to_id = fm.from_id
			WHERE to_id = #{id}
			GROUP BY to_id;
			
			""")
	@Results({
		@Result(property = "followerCount", column = "follower"),
		@Result(property = "followingCount", column = "following"),
	})
	FollowCountResponse selectFollowCountById(String id);
	
	@Select("""
			SELECT t.user_id, success, total, success / total * 100 rate
			FROM (
				SELECT p.user_id, count(*) total
				FROM plans p
				JOIN courses c ON p.id = c.plan_id
				GROUP BY p.user_id) t
			JOIN (
				SELECT p.user_id, count(*) success
				FROM plans p
				JOIN courses c ON p.id = c.plan_id
				JOIN quests q ON c.id = q.id
				WHERE q.isClear = 1
				GROUP BY p.user_id ) s
			ON t.user_id = s.user_id
			WHERE t.user_id = #{id}
			
			""")
	@Results({
		@Result(property = "successQuestRate", column = "rate"),
		@Result(property = "successQuestCount", column = "success"),
		@Result(property = "totalQuestCount", column = "total")
	})
	QuestRateResponse selectQuestCountById(String id);
	
	@Select("SELECT * from users WHERE id=#{id}")
	@Results({
		@Result(property = "userInfo", column="id", one = @One(select = "selectUserById")),
		@Result(property = "followCount", column = "id", one=@One(select = "selectFollowCountById")),
		@Result(property = "questRate", column = "id", one=@One(select = "selectQuestCountById")),
	})
	StatusUserInfoResponse selectUserInfoById(String id);

	@SelectProvider(type = UserSqlProvider.class, method = "selectNameFromAchievementById")
	String selectNameFromAchievementById(int achievementId, String achievementTable);

	@Select("""
			SELECT s.sido_code, ifnull(v.count, 0) count
			FROM sidos s
			LEFT JOIN (
				SELECT *
				FROM visit_counts
				WHERE user_id = #{id}) v
			ON s.sido_code = v.sido_code
			ORDER BY s.no
			
			""")
	@Results({
		@Result(property = "sidoCode", column = "sido_code"),
		@Result(property = "visitCount", column = "count")
	})
	List<StatusMapCountResponse> selectVisitCountById(String id);

	@Select("""
			SELECT ah.name, ah.content, ah.img, ifnull(aq.count, 0) is_acquire
			FROM achievement_tier ah
			LEFT JOIN (
				SELECT achievement_id, count(*) count
				FROM acquire_tier
				WHERE user_id = #{id}
				GROUP BY achievement_id ) aq
			ON ah.id = aq.achievement_id
			""")
	@Results({
		@Result(property = "achievementName", column = "name"),
		@Result(property = "achievementContent", column = "content"),
		@Result(property = "achievementImg", column = "img"),
		@Result(property = "isAcquire", column = "is_acquire"),
	})
	List<UserAchievementTierResponse> selectAchievementTierById(String id);

	
	@Select("""
			SELECT ah.name, ah.content, ah.img, vc.count, ah.visit_count, ifnull(aq.count, 0) is_acquire
			FROM achievement_visit ah
			LEFT JOIN (
				SELECT achievement_id, count(*) count
				FROM acquire_visit
				WHERE user_id = #{id}
				GROUP BY achievement_id ) aq
			ON ah.id = aq.achievement_id
			JOIN visit_counts vc
			ON ah.sido_code = vc.sido_code
			""")
	@Results({
		@Result(property = "achievementName", column = "name"),
		@Result(property = "achievementContent", column = "content"),
		@Result(property = "achievementImg", column = "img"),
		@Result(property = "currentVisitCount", column = "count"),
		@Result(property = "acquireVisitCount", column = "visit_count"),
		@Result(property = "isAcquire", column = "is_acquire"),
	})
	List<UserAchievementVisitResponse> selectAchievementVisitById(String id);

	@Select("SELECT * from users ORDER BY exp DESC")
	@Results({
		@Result(property = "profileImg", column = "profile_img"),
		@Result(property = "profileDesc", column = "profile_desc"),
		@Result(property = "achievementTable", column = "achievement_table"),
		@Result(property = "achievementId", column = "achievement_id"),
		@Result(property = "tierInfo", column="tier_no", one = @One(select = "selectTierById"))
	})
	List<UserInfoResponse> selectUserOrderByExp();

}
