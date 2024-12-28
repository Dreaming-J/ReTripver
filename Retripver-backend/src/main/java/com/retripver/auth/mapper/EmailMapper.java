package com.retripver.auth.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.retripver.auth.dto.EmailAuthResponse;
import com.retripver.auth.dto.EmailCodeRequest;

@Mapper
public interface EmailMapper {

	@Select("SELECT count(*) FROM email_auth")
	int selectCountByEmail(String email);

	@Delete("DELETE FROM email_auth WHERE email = #{email}")
	void deleteByEmail(String email);

	@Insert("INSERT INTO email_auth VALUES (#{email}, #{code}, #{completeTime})")
	void insert(EmailCodeRequest emailCodeRequest);

	@Select("SELECT * FROM email_auth WHERE email = #{email}")
	@Results({
		@Result(property = "completeTime", column = "complete_time")
	})
	EmailAuthResponse selectById(String email);

}
