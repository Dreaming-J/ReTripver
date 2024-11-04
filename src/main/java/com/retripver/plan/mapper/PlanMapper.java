package com.retripver.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.retripver.plan.dto.PlanResponse;

@Mapper
public interface PlanMapper {

	@Select("SELECT * FROM plans WHERE user_id=#{userId}")
	List<PlanResponse> selectPlanByUserId(String userId);

}
