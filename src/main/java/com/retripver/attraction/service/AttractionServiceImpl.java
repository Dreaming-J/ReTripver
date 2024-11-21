package com.retripver.attraction.service;

import static com.retripver.global.constant.Constant.PAGE_SIZE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retripver.attraction.dto.AttractionResponse;
import com.retripver.attraction.dto.SidoResponse;
import com.retripver.attraction.repository.AttractionRepository;
import com.retripver.plan.exception.NotFoundAttractionException;

@Service
public class AttractionServiceImpl implements AttractionService {

	private final AttractionRepository attractionRepository;
	
	@Autowired
	public AttractionServiceImpl(AttractionRepository attractionRepository) {
		this.attractionRepository = attractionRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public AttractionResponse getAttraction(int attractionNo) {
		AttractionResponse attractionResponse = attractionRepository.getAttraction(attractionNo);
		
		if (attractionResponse == null)
			throw new NotFoundAttractionException();
		
		return attractionResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public List<AttractionResponse> getAttractions(int sidoCode, int page) {
		Map<String, Object> params = new HashMap<>();
		params.put("sidoCode", sidoCode);
		params.put("page", (page - 1) * PAGE_SIZE);
		params.put("size", PAGE_SIZE);
		
		List<AttractionResponse> attractionList = attractionRepository.getAttractions(params);
		
		return attractionList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SidoResponse> getSidos() {
		return attractionRepository.getSidos();
	}

}
