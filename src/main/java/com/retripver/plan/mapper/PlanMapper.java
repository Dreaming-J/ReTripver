package com.retripver.plan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.retripver.plan.dto.CourseRequest;
import com.retripver.plan.dto.CourseResponse;
import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;

@Mapper
public interface PlanMapper {
	
	final String ATTRACTION_MAPPER = "com.retripver.attraction.mapper.AttractionMapper.";

	
    @Select("""
    		SELECT *
    		FROM courses
    		WHERE plan_id = #{planId}
            ORDER BY course_order
    		""")
	@Results({
	    @Result(property = "attraction", column = "attraction_no", one = @One(select = ATTRACTION_MAPPER + "selectAttractionByAttractionNo"))
	    })
	List<CourseResponse> selectCoursesByPlanId(int planId);
    
	@Select("""
			SELECT *
			FROM plans
			WHERE user_id = #{userId}
			AND is_public = true
			""")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "courses", column = "id", many = @Many(select = "selectCoursesByPlanId"))
	    })
	List<PlanResponse> selectPlansByUserId(String userId);

	@Select("""
			SELECT *
			FROM plans
			WHERE user_id = #{userId}
			""")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "courses", column = "id", many = @Many(select = "selectCoursesByPlanId"))
	    })

	List<PlanResponse> selectMyPlansByUserId(String userId);

    @Select("""
    		SELECT *
    		FROM plans
    		WHERE id = #{planId}
    		""")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "courses", column = "id", many = @Many(select = "selectCoursesByPlanId"))
	    })
	PlanResponse selectPlanByPlanId(int planId);

	@Select("""
			SELECT *
			FROM plan_like pl JOIN plans p
			ON pl.plan_id = p.id
			WHERE pl.user_id = #{userId}
			AND p.is_public = true
			""")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "courses", column = "id", many = @Many(select = "selectCoursesByPlanId"))
	    })
	List<PlanResponse> selectLikePlansByUserId(String userId);

	@Select("""
			SELECT COUNT(*)
			FROM plan_like
			WHERE user_id = #{userId}
			AND plan_id = #{planId}
			""")
	int selectLikePlanByUserIdAndPlanId(Map<String, Object> params);

	@Select("""
			SELECT p.id, p.user_id, p.title, p.sido_code, s.sido_name, IFNULL(pl.likeCount, 0) likeCount, ROW_NUMBER() OVER(ORDER BY likeCount DESC) `rank`
			FROM plans p
			LEFT JOIN (SELECT plan_id, COUNT(plan_id) likeCount
						FROM plan_like
						GROUP BY plan_id) as pl
			ON p.id = pl.plan_id
			JOIN sidos s
			ON p.sido_code = s.sido_code
			WHERE p.is_public = true
			GROUP BY p.id
			ORDER BY likeCount DESC
			LIMIT #{page}, #{size}
			""")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "courses", column = "id", many = @Many(select = "selectCoursesByPlanId"))
	    })
	List<PlanResponse> selectRankPlans(Map<String, Object> params);

	@Insert("""
			INSERT INTO plan_like(plan_id, user_id)
			VALUES (#{planId}, #{userId})
			""")
	int insertPlanLike(Map<String, Object> params);

	@Delete("""
			DELETE FROM plan_like
			WHERE plan_id = #{planId}
			AND user_id = #{userId}
			""")
	int deletePlanLike(Map<String, Object> params);

	@Insert("""
			INSERT INTO plans(user_id, title, sido_code, is_public)
			VALUES (#{userId}, #{title}, #{sidoCode}, #{isPublic})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	int insertPlan(PlanRequest planRequest);

	@Insert("""
			<script>
				INSERT INTO courses(plan_id, attraction_no, course_order, mission_img, exp)
				VALUES
				<foreach collection='courses' item='course' separator=','>
					(#{planId}, #{course.attractionNo}, #{course.courseOrder}, #{course.missionImg}, #{course.exp})
				</foreach>
			</script>
			""")
	int insertCourses(int planId, List<CourseRequest> courses);

	@Select("""
			SELECT *
			FROM plans
			WHERE sido_code = #{sidoCode}
			AND is_public = true
			""")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "courses", column = "id", many = @Many(select = "selectCoursesByPlanId"))
	    })
	List<PlanResponse> selectPlansBySidoCode(int sidoCode);

    @Select("""
    		SELECT COUNT(*)
    		FROM courses
    		WHERE plan_id = #{planId}
    		""")
	int selectCourseSizeByPlanId(int planId);
    
    @Select("""
    		SELECT COUNT(*)
    		FROM courses
    		WHERE plan_id = #{planId}
    		AND user_img IS NOT NULL
    		""")
	int selectCarryOutCourseSizeByPlanId(int planId);

    @Select("""
    		SELECT SUM(exp)
    		FROM courses
    		WHERE plan_id = #{planId}
    		AND is_clear = TRUE
    		GROUP BY plan_id
    		""")
	int selectSumExpOfClearCoursesByPlanId(int planId);

    @Update("""
    		UPDATE users
    		SET exp = exp + #{gainExp}
    		WHERE id = #{userId}
    		""")
	void updateExpByUserId(int gainExp, String userId);
}
