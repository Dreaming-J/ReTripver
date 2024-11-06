package com.retripver.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.PwdModifyRequest;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.dto.UserModifyRequest;
import com.retripver.user.dto.UserProfileRequest;
import com.retripver.user.dto.UserSearchIdRequest;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM users WHERE id=#{id} AND password=#{password}")
	LoginResponse selectByIdAndPassword(LoginRequest loginRequest);

	@Insert("INSERT INTO users (id, password, name, email) VALUES (#{id}, #{password}, #{name}, #{email})")
	void insert(SignupRequest signupRequest);
	
	@Select("SELECT count(*) FROM users WHERE id=#{id}")
	int selectCountById(String id);

	@Update("UPDATE users SET profile_img=#{profileImg}, profile_desc=#{profileDesc} WHERE id=#{id}")
	void updateProfile(UserProfileRequest userProfileRequest);

	@Select("SELECT id FROM users WHERE name=#{name} AND email=#{email}")
	String selectByNameAndEmail(UserSearchIdRequest userSearchIdRequest);

	@Update("UPDATE users SET name=#{name}, email=#{email}, profile_img=#{profileImg}, profile_desc=#{profileDesc} WHERE id=#{id}")
	void update(UserModifyRequest userModifyRequest);

	@Update("UPDATE users SET password=#{newPassword} WHERE id=#{id}")
	void updatePassword(PwdModifyRequest pwdModifyRequset);

	

}
