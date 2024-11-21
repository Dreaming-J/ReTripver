package com.retripver.auth.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.retripver.auth.dto.LoginRequest;
import com.retripver.auth.dto.LoginResponse;
import com.retripver.auth.dto.PwdModifyRequest;
import com.retripver.auth.dto.SignupRequest;
import com.retripver.auth.dto.UserModifyRequest;
import com.retripver.auth.dto.UserProfileRequest;
import com.retripver.auth.dto.UserSearchIdRequest;
import com.retripver.auth.dto.UserSearchPwdRequest;

@Mapper
public interface AuthMapper {
	
    @Delete("DELETE FROM black_list WHERE expired_at < NOW()")
	void deleteExpiredTokens();

	@Select("SELECT COUNT(*) FROM black_list WHERE token=#{token}")
	boolean selectBlackListByToken(String token);

	@Select("SELECT * FROM users WHERE id = #{id} AND password = #{password}")
	LoginResponse selectByIdAndPassword(LoginRequest loginRequest);

	@Insert("INSERT INTO users (id, password, salt, name, email) VALUES (#{id}, #{password}, #{salt}, #{name}, #{email})")
	void insert(SignupRequest signupRequest) throws SQLException;

	@Delete("DELETE FROM users WHERE id = #{id}")
	void deleteUser(String id) throws SQLException;
	
	@Select("SELECT salt FROM users WHERE id = #{id}")
	String selectSaltById(String id);
	
	@Select("SELECT count(*) FROM users WHERE id = #{id}")
	int selectCountById(String id);
	
	@Select("SELECT count(*) FROM users WHERE email = #{email}")
	int selectCountByEmail(String email);
	
	@Update("UPDATE users SET profile_img = #{profileImg}, profile_desc = #{profileDesc} WHERE id = #{id}")
	void updateProfile(UserProfileRequest userProfileRequest) throws SQLException;

	@Select("SELECT id FROM users WHERE name = #{name} AND email = #{email}")
	String selectByNameAndEmail(UserSearchIdRequest userSearchIdRequest);
	
	@Select("SELECT password users WHERE id = #{id} AND name = #{name} AND email = #{email}")
	String selectByIdAndNameAndEmail(UserSearchPwdRequest userSearchPwdRequest);

	@Update("UPDATE users SET id = #{id}, name = #{name}, email = #{email}, profile_img = #{profileImg}, profile_desc = #{profileDesc} WHERE id = #{curId}")
	void update(UserModifyRequest userModifyRequest) throws SQLException;

	@Update("UPDATE users SET password = #{newPassword} WHERE id = #{id}")
	void updatePassword(PwdModifyRequest pwdModifyRequset) throws SQLException;
	
	@Select("SELECT passowrd FROM users WHERE id = #{id}")
	String selectPasswordById(String id);
	
}
