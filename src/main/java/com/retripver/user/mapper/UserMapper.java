package com.retripver.user.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.retripver.user.dto.FollowCountResponse;
import com.retripver.user.dto.FollowRequest;
import com.retripver.user.dto.QuestRateResponse;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.TierInfoResponse;
import com.retripver.user.dto.UserAchievementTierResponse;
import com.retripver.user.dto.UserAchievementVisitResponse;
import com.retripver.user.dto.UserInfoResponse;

@Mapper
public interface UserMapper {

	@Select("SELECT count(*) FROM follow WHERE from_id = #{fromId} AND to_id = #{toId}")
	int selectCountByFromIdAndToId(FollowRequest followRequest);

	@Delete("DELETE FROM follow WHERE from_id = #{fromId} AND to_id = #{toId}")
	void deleteFollow(FollowRequest followRequest) throws SQLException;

	@Insert("INSERT INTO follow VALUES (#{fromId}, #{toId})")
	void insertFollow(FollowRequest followRequest) throws SQLException;
	
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
			SELECT 
			COALESCE(COUNT(ft.to_id), 0) as follower, 
			COALESCE(following, 0) as following
			FROM (SELECT #{id} as to_id) u
			LEFT JOIN follow ft ON u.to_id = ft.to_id
			LEFT JOIN (
				SELECT from_id, COUNT(*) following
				FROM follow
				GROUP BY from_id
			) fm ON ft.to_id = fm.from_id
			GROUP BY u.to_id;
			""")
	@Results({
		@Result(property = "followerCount", column = "follower"),
		@Result(property = "followingCount", column = "following"),
	})
	FollowCountResponse selectFollowCountById(String id);
	
	@Select("""
			SELECT u.id, IFNULL(success, 0) success, IFNULL(total, 0) total, IFNULL(success, 0) / IFNULL(total, 1) rate
            FROM users u
			LEFT JOIN (
				SELECT p.user_id, count(*) total
				FROM plans p
				GROUP BY p.user_id) t
			ON u.id = t.user_id
			LEFT JOIN (
				SELECT p.user_id, count(*) success
				FROM plans p
				WHERE p.is_clear = 1
				GROUP BY p.user_id ) s
			ON t.user_id = s.user_id
			WHERE u.id = #{id}
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

	@Select("""
			<script>
				SELECT name
				FROM ${achievementTable}
				WHERE id = #{achievementId}
			</script>
			""")
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

	@Select("""
			SELECT *
			FROM users
			JOIN visit_counts 
			ON id = user_id
			WHERE sido_code = #{sidoCode}
			ORDER BY count DESC
			""")
	@Results({
		@Result(property = "profileImg", column = "profile_img"),
		@Result(property = "profileDesc", column = "profile_desc"),
		@Result(property = "achievementTable", column = "achievement_table"),
		@Result(property = "achievementId", column = "achievement_id"),
		@Result(property = "tierInfo", column="tier_no", one = @One(select = "selectTierById"))
	})
	List<UserInfoResponse> selectUserOrderByVisitCount(int sidoCode);

//	@Select("SELECT * from users WHERE id LIKE CONCAT('%', #{keyword}, '%')")
	
	@Select("""
			SELECT * 
			FROM users
			WHERE id LIKE CONCAT('%', #{keyword}, '%') 
			ORDER BY
				CASE
					WHEN id LIKE CONCAT(#{keyword}, '%') THEN 1
					WHEN id LIKE CONCAT('%', #{keyword}, '%') THEN 2
					ELSE 3 END, id asc;
			""")
	@Results({
		@Result(property = "profileImg", column = "profile_img"),
		@Result(property = "profileDesc", column = "profile_desc"),
		@Result(property = "achievementTable", column = "achievement_table"),
		@Result(property = "achievementId", column = "achievement_id"),
		@Result(property = "tierInfo", column="tier_no", one = @One(select = "selectTierById"))
	})
	List<UserInfoResponse> selectUserSearchByKeyword(String keyword);
}
