package com.retripver.plan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.retripver.plan.dto.CourseResponse;
import com.retripver.plan.dto.PlanResponse;

@Mapper
public interface PlanMapper {

	@Select("SELECT * " +
			"FROM plans " +
			"WHERE user_id = #{userId} ")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "courses", column = "id", many = @Many(select = "selectCoursesByPlanId"))
	    })
	List<PlanResponse> selectPlansByUserId(String userId);

    @Select("SELECT * " +
            "FROM courses " +
            "WHERE plan_id = #{planId} " +
            "ORDER BY course_order ")
	List<CourseResponse> selectCoursesByPlanId(int planId);

    @Select("SELECT * " +
    		"FROM plans " +
    		"WHERE id = #{planId} ")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "courses", column = "id", many = @Many(select = "selectCoursesByPlanId"))
	    })
	PlanResponse selectPlanByPlanId(int planId);

	@Select("SELECT * " +
			"FROM plan_like pl JOIN plans p " +
			"ON pl.plan_id = p.id " +
			"WHERE pl.user_id = #{userId} ")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "courses", column = "id", many = @Many(select = "selectCoursesByPlanId"))
	    })
	List<PlanResponse> selectLikePlansByUserId(String userId);

	@Select("SELECT p.id, p.user_id, p.title, p.sido_code, COALESCE(COUNT(plan_id), 0) likeCount " +
			"FROM plans p LEFT JOIN plan_like pl " +
			"ON p.id = pl.plan_id " +
			"WHERE p.is_public = true " +
			"GROUP BY p.id " +
			"ORDER BY likeCount DESC " +
			"LIMIT #{page}, #{size} ")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "courses", column = "id", many = @Many(select = "selectCoursesByPlanId"))
	    })
	List<PlanResponse> selectRankPlans(Map<String, Object> params);
}
