package com.retripver.user.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.retripver.user.dto.EmailCodeRequest;

@Mapper
public interface EmailMapper {

	@Select("SELECT count(*) FROM email_auth")
	int selectCountByEmail(String email);

	@Delete("DELETE FROM email_auth WHERE email = #{email}")
	void deleteByEmail(String email);

	@Insert("INSERT INTO email_auth (email, code) VALUES (#{email}, #{code})")
	void insert(EmailCodeRequest emailCodeRequest);

}
