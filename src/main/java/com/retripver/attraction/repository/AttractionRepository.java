package com.retripver.attraction.repository;

import java.util.List;
import java.util.Map;

import com.retripver.attraction.dto.AttractionResponse;
import com.retripver.attraction.dto.SidoResponse;

public interface AttractionRepository {

	AttractionResponse getAttraction(int attractionNo);

	List<AttractionResponse> getAttractions(Map<String, Object> params);

	List<SidoResponse> getSidos();

}
