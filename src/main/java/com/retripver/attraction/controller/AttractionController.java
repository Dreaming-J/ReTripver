package com.retripver.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retripver.attraction.dto.AttractionResponse;
import com.retripver.attraction.dto.SidoResponse;
import com.retripver.attraction.service.AttractionService;

@RestController
@RequestMapping("/attraction")
public class AttractionController {

	private final AttractionService attractionService;
	
	@Autowired
	public AttractionController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}
	
	@GetMapping("/info/{attractionNo}")
	public ResponseEntity<?> attractionInfo(@PathVariable("attractionNo") int attractionNo) {
		AttractionResponse attractionResponse = attractionService.getAttraction(attractionNo);
		
		return ResponseEntity.ok(attractionResponse);
	}
	
	@GetMapping("/search/{sidoCode}")
	public ResponseEntity<?> attractionList(@PathVariable("sidoCode") int sidoCode, @RequestParam int page) {
		List<AttractionResponse> attractionList = attractionService.getAttractions(sidoCode, page);
		
		return ResponseEntity.ok(attractionList);
	}
	
	@GetMapping("/sidos")
	public ResponseEntity<?> sidoList() {
		List<SidoResponse> sidoList = attractionService.getSidos();
		
		return ResponseEntity.ok(sidoList);
	}
}
