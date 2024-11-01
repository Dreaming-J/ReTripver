package com.retripver.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.retripver.plan.dto.PlanResponse;

@Mapper
public interface PlanMapper {

	List<PlanResponse> selectPlanByUserId(int userId);

}
