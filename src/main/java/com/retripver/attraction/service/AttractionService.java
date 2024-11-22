package com.retripver.attraction.service;

import java.util.List;

import com.retripver.attraction.dto.AttractionResponse;
import com.retripver.attraction.dto.SearchOption;
import com.retripver.attraction.dto.SidoResponse;

public interface AttractionService {

	AttractionResponse getAttraction(int attractionNo);

	List<AttractionResponse> getAttractions(SearchOption searchOption);

	List<SidoResponse> getSidos();

}
