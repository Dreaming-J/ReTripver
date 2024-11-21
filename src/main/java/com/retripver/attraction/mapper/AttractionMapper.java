package com.retripver.attraction.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.retripver.attraction.dto.AttractionResponse;
import com.retripver.attraction.dto.GugunResponse;
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
    		SELECT *
    		FROM attractions a
    		JOIN sidos s ON a.area_code = s.sido_code
    		JOIN guguns g ON a.area_code = g.sido_code AND a.si_gun_gu_code = g.gugun_code
    		JOIN contenttypes c ON a.content_type_id = c.content_type_id
    		WHERE a.area_code = #{sidoCode}
    		AND a.first_image1 <> ''
    		LIMIT #{page}, #{size}
    		""")
	List<AttractionResponse> selectAttractionsBySidoCode(Map<String, Object> params);

    @Select("SELECT * FROM sidos")
	@Results({
		@Result(property = "sidoCode", column = "sido_code"),
	    @Result(property = "guguns", column = "sido_code", many = @Many(select = "selectGugunsBySidoCode"))
	    })
	List<SidoResponse> selectSidos();

    @Select("SELECT * FROM guguns WHERE sido_code = #{sidoCode}")
    List<GugunResponse> selectGugunsBySidoCode(int sidoCode);
}
