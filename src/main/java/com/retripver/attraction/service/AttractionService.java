package com.retripver.attraction.service;

import java.util.List;

import com.retripver.attraction.dto.AttractionResponse;
import com.retripver.attraction.dto.SidoResponse;

public interface AttractionService {

	AttractionResponse getAttraction(int attractionNo);

	List<AttractionResponse> getAttractions(int sidoCode, int page);

	List<SidoResponse> getSidos();

}
