package com.retripver.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM users WHERE id=#{id} AND password=#{password}")
	LoginResponse selectByIdAndPassword(LoginRequest loginRequest);

}
