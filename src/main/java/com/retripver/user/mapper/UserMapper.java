package com.retripver.user.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.PwdModifyRequest;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.TierInfoResponse;
import com.retripver.user.dto.UserInfoResponse;
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

	@Delete("DELETE FROM users WHERE id=#{id}")
	void deleteUser(String id);

	@Select("SELECT count(*) FROM users WHERE from_id=#{fromId} AND to_id=#{toId}")
	int selectCountByFromIdAndToId(String fromId, String toId);

	@Delete("DELETE FROM usrs WHERE from_id=#{fromId} AND to_id=#{toId}")
	void deleteFollow(String fromId, String toId);

	@Insert("INSERT INTO follow VALUES (#{fromId}, #{toId})")
	void insertFollow(String fromId, String toId);
	
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
		@Result(property = "tierInfo", column="tier_no", one = @One(select = "selectTierById"))
	})
	UserInfoResponse selectUserById(String id);
	
	
	
	
	
//	StatusUserInfoResponse selectUserInfoById(String id);

	

}
