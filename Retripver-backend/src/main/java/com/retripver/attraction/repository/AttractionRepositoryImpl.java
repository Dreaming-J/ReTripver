package com.retripver.attraction.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retripver.attraction.dto.AttractionResponse;
import com.retripver.attraction.dto.SearchOption;
import com.retripver.attraction.dto.SidoResponse;
import com.retripver.attraction.mapper.AttractionMapper;

@Repository
public class AttractionRepositoryImpl implements AttractionRepository {

	private final AttractionMapper attractionMapper;
	
	@Autowired
	public AttractionRepositoryImpl(AttractionMapper attractionMapper) {
		this.attractionMapper = attractionMapper;
	}

	@Override
	public AttractionResponse getAttraction(int attractionNo) {
		return attractionMapper.selectAttractionByAttractionNo(attractionNo);
	}

	@Override
	public List<AttractionResponse> getAttractions(SearchOption searchOption) {
		return attractionMapper.selectAttractionsBySidoCode(searchOption);
	}

	@Override
	public List<SidoResponse> getSidos() {
		return attractionMapper.selectSidos();
	}

	@Override
	public String getSidoName(int sidoCode) {
		return attractionMapper.selectSidoNameBySidoCode(sidoCode);
	}
	
}
