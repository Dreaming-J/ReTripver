package com.retripver.attraction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.retripver.attraction.dto.AttractionResponse;
import com.retripver.attraction.dto.GugunResponse;
import com.retripver.attraction.dto.SearchOption;
import com.retripver.attraction.dto.SidoResponse;

@Mapper
public interface AttractionMapper {
    
    @Select("""
    		SELECT *
    		FROM attractions a
    		JOIN sidos s ON a.area_code = s.sido_code
    		JOIN guguns g ON a.area_code = g.sido_code AND a.si_gun_gu_code = g.gugun_code
    		JOIN contenttypes c ON a.content_type_id = c.content_type_id
    		WHERE a.no = #{attractionNo}
    		""")
    AttractionResponse selectAttractionByAttractionNo(int attractionNo);

    @Select("""
    		<script>
	    		SELECT *
	    		FROM attractions a
	    		JOIN sidos s ON a.area_code = s.sido_code
	    		JOIN guguns g ON a.area_code = g.sido_code AND a.si_gun_gu_code = g.gugun_code
	    		JOIN contenttypes c ON a.content_type_id = c.content_type_id
	    		<where>
	    			<if test='sidoCode != null and sidoCode != 0'>
	    				AND a.area_code = #{sidoCode}
	    			</if>
	    			<if test='gugunCode != null and gugunCode != 0'>
	    				AND g.gugun_code = #{gugunCode}
	    			</if>
	    			<if test="keyword != null and keyword != ''">
	    				AND a.title LIKE CONCAT('%', #{keyword}, '%')
	    			</if>
	    			AND a.first_image1 != ''
	    		</where>
	    		LIMIT #{page}, #{size}
    		</script>
    		""")
	List<AttractionResponse> selectAttractionsBySidoCode(SearchOption searchOption);

    @Select("SELECT * FROM sidos")
	@Results({
		@Result(property = "sidoCode", column = "sido_code"),
	    @Result(property = "guguns", column = "sido_code", many = @Many(select = "selectGugunsBySidoCode"))
	    })
	List<SidoResponse> selectSidos();

    @Select("SELECT * FROM guguns WHERE sido_code = #{sidoCode}")
    List<GugunResponse> selectGugunsBySidoCode(int sidoCode);

    @Select("SELECT sido_name FROM sidos WHERE sido_code = #{sidoCode}")
	String selectSidoNameBySidoCode(int sidoCode);
}
